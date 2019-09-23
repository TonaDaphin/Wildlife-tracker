import static spark.Spark.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import spark.ModelAndView;
import spark.template.handlebars.HandlebarsTemplateEngine;


public class App {

    public static void main(String[] args) {

        staticFileLocation("/public");

//        get("/", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            return new ModelAndView(model, "index.hbs");
//        }, new HandlebarsTemplateEngine());
//
        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("ranger", Ranger.all());
            return new ModelAndView(model, "index.hbs");
        }, new HandlebarsTemplateEngine());

        //show new ranger form
        get("/ranger/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new ModelAndView(model, "ranger-form.hbs");
        }, new HandlebarsTemplateEngine());

        //ranger: process new ranger form
        post("/ranger", (req, res) -> { //URL to make new task on POST route
            Map<String, Object> model = new HashMap<>();
            String rangerName = req.queryParams("rangerName");
            String rangerBadge = req.queryParams("rangerBadge");
            if (!(rangerName.trim().isEmpty() || rangerBadge.trim().isEmpty())) {

                Ranger ranger = new Ranger(rangerName, Integer.parseInt(rangerBadge));
                ranger.save();
            } else {
                System.out.println("Please fill in all the fields");
            }
            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());

        //get: show an individual ranger
        get("/ranger/:id", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(req.params(":id")));
            model.put("ranger", ranger);
            return new ModelAndView(model, "ranger.hbs");

        }, new HandlebarsTemplateEngine());

        get("/ranger/:id/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            Ranger ranger = Ranger.find(Integer.parseInt(req.params(":id")));
            model.put("ranger", ranger);
            model.put("animals", Animals.all());
            model.put("location", Location.all());
            model.put("sighting", Sighting.find(Integer.parseInt(req.params(":id"))));
            return new ModelAndView(model, "sighting-form.hbs");

        }, new HandlebarsTemplateEngine());

        post("/sighting", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String rangerName = req.queryParams("rangerName");
            String animal = req.queryParams("animal");
            int id = Integer.parseInt(req.queryParams("id"));
            String location = req.queryParams("location");


            Sighting sighting = new Sighting(location, rangerName, id, animal);
            sighting.save();

            res.redirect("/");
            return null;
        }, new HandlebarsTemplateEngine());


        ///////////////Animals
        get("/animals", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("animals", Animals.all());
            model.put("endangereds", Endangered.allEndangered());
            return new ModelAndView(model, "animals.hbs");
        }, new HandlebarsTemplateEngine());

        //post
        post("/animals/new", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            String name = req.queryParams("name");
            String health = req.queryParams("health");
            String age = req.queryParams("age");
            if (req.queryParams("endangereds") != null) {
                if (!(name.trim().isEmpty() || health.trim().isEmpty() || age.trim().isEmpty())) {

                    Endangered endangereds = new Endangered(name, health, age);
                    endangereds.save();
                } else {
                    System.out.println("Please fill in all the fields");
                }
            } else {
                if (!(name.trim().isEmpty())) {
                    Animals animal = new Animals(name);
                    animal.save();
                } else {
                    System.out.println("Please fill in all the fields");
                }


            }
            res.redirect("/animals");
            return null;
        }, new HandlebarsTemplateEngine());

        get("/sighting", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("sighting", Sighting.all());

            return new ModelAndView(model, "sighting.hbs");

        }, new HandlebarsTemplateEngine());

        get("/location", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            model.put("location", Location.all());
            return new ModelAndView(model, "location.hbs");
        }, new HandlebarsTemplateEngine());

        post("/location", (req, res) -> {
            Map<String, Object> model = new HashMap<>();

            String locationName = req.queryParams("name");

            if (!(locationName.trim().isEmpty())) {

                Location location = new Location(locationName);
                location.save();
            }
            res.redirect("/location");
            return null;
        },new HandlebarsTemplateEngine());
    }
}