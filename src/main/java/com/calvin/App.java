package com.calvin;

import com.calvin.Repository.ItemRepository;
import com.calvin.Repository.UserRepository;
import com.calvin.model.GiftItem;
import com.calvin.model.Transaction;
import com.calvin.model.User;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Version;
import spark.ModelAndView;
import spark.Request;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static spark.Spark.*;

/**
 * Hello world!
 */
public class App {

    private static String code;
    private static FreeMarkerEngine freeMarkerEngine;
    private static UserRepository userRepository;
    private static ItemRepository itemRepository;

    public static void main(String[] args) {
        port(8080);
        staticFileLocation("asset/");
        setupRoutes();
        setFreeMarkerEngine();

        try {
            userRepository = new UserRepository();
            itemRepository = new ItemRepository();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void setFreeMarkerEngine() {
        freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration(new Version("2.3.23"));
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(App.class, "/"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
    }

    private static HashMap<String, Object> GetSessionMap(Request request) {
        HashMap<String, Object> sessionObject = new HashMap<>();
        if (request.session().attribute("user") != null) {
            sessionObject.put("user", request.session().attribute("user"));
        }
        return sessionObject;
    }

    private static void setupRoutes() {
        path("/gift_card_service", () -> {
            get("/", (request, response) -> {
                Map<String, Object> value = GetSessionMap(request);
                value.put("items", itemRepository.getAllGiftItems());
                return render("home.ftl", value);
            });

            get("/item/id/:id", (request, response) -> {
                int id = Integer.parseInt(request.params("id"));
                GiftItem giftItem = itemRepository.getItemById(id);
                Map<String, Object> message = GetSessionMap(request);
                message.put("item", giftItem);
                return render("item_detail.ftl", message);
            });


            get("/login", (request, response) -> render("login.ftl", null));

            get("/reg", (request, response) -> {
                Object value = request.session().attribute("user") == null ? null : GetSessionMap(request);
                return render("register.ftl", value);
            });

            post("/reg", (request, response) -> {
                int result;
                String fullName = request.queryParams("fullname");
                String email = request.queryParams("email");
                String username = request.queryParams("username");
                String password = request.queryParams("password");

                Map<String, Object> message = GetSessionMap(request);

                if (request.session().attribute("user") != null) {
                    int userId = Integer.parseInt(request.queryParams("id"));
                    // this is an update
                    result = userRepository.update(fullName, email, username, password, userId);
                    message.put("message", "User profile updated successfully.");
                    message.put("regUser", userRepository.getUserByUsername(username));
                    return render("register.ftl", message);
                } else {
                    // this is an insert
                    result = userRepository.create(fullName, email, username, password);
                    message.put("message", "User has been registered");
                }

                if (result == 1) {
                    return render("login.ftl", message);
                } else {
                    return null;
                }
            });

            post("/login", (request, response) -> {
                if (userRepository.login(request.queryParams("username"), request.queryParams("password"))) {
                    Session session = request.session(true);
                    session.attribute("user", request.queryParams("username"));
                    session.attribute("user_id", userRepository.getUserByUsername(request.queryParams("username")).getId());
                    response.redirect("/gift_card_service/");
                    return "";
                } else {
                    Map<String, Object> message = new HashMap<>();
                    message.put("error", "Username and password not found.");
                    return render("login.ftl", message);
                }
            });

            path("/u", () -> {

                before("/*", (request, response) -> {
                    if (request.session().attribute("user") == null) {
                        response.redirect("/gift_card_service/login");
                    }
                });

                get("/", (request, response) -> {
                    if (request.session().attribute("user") == null) {
                        response.redirect("login");
                    }

                    HashMap<String, Object> message = GetSessionMap(request);

                    User user = userRepository.getUserByUsername(request.session().attribute("user"));
                    ArrayList<Transaction> transactions = itemRepository.GetAllTransaction(user.getId());

                    message.put("transactions", transactions);
                    return render("transaction_history.ftl", message);
                });

                get("/logout", (request, response) -> {
                    request.session().removeAttribute("user");
                    Map<String, Object> message = new HashMap<>();
                    message.put("message", "You have been logged out.");
                    return render("login.ftl", message);
                });

                get("/buy/id/:id", (request, response) -> {
                    int id = Integer.parseInt(request.params("id"));
                    GiftItem giftItem = itemRepository.getItemById(id);
                    Map<String, Object> message = GetSessionMap(request);
                    message.put("item", giftItem);
                    return render("confirmation.ftl", message);
                });

                get("/confirm/:id", (request, response) -> {
                    int itemId = Integer.parseInt(request.params("id"));

                    code = generateRandomCode();

                    User user = userRepository.getUserByUsername(request.session().attribute("user"));
                    int result = itemRepository.createUserOrder(user.getId(), itemId, code);

                    if (result == 1) {
                        response.redirect("../success");
                    }
                    return "";
                });

                get("/success", (request, response) -> {

                    Map<String, Object> message = GetSessionMap(request);
                    message.put("voucher", code);
                    return render("success.ftl", message);
                });

                get("/profile", (request, response) -> {
                    Map<String, Object> message = request.session().attribute("user") == null ? null : GetSessionMap(request);
                    User user = userRepository.getUserByUsername(request.session().attribute("user"));
                    message.put("regUser", user);

                    return render("register.ftl", message);
                });

                get("/history", ((request, response) -> render("transaction_history.ftl", GetSessionMap(request))));
            });
        });
    }

    private static String generateRandomCode() {
        return UUID.randomUUID().toString();
    }

    private static String render(String filePath, Object model) {
        return freeMarkerEngine.render(new ModelAndView(model, filePath));
    }

}
