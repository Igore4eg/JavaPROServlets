package com.gmail.viazminiv;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/anketa")
public class AnketaServlet extends HttpServlet {

    private Map<Integer, Map<String, Integer>> stat = new HashMap<>();
    public void init() throws ServletException {
        stat.put(1, new HashMap<String, Integer>());
        stat.put(2, new HashMap<String, Integer>());
        stat.get(1).put("beginner", 0);
        stat.get(1).put("elementary", 0);
        stat.get(1).put("intermediate", 0);
        stat.get(1).put("upper intermediate", 0);
        stat.get(1).put("advanced", 0);
        stat.get(1).put("proficiency", 0);
        stat.get(2).put("Java", 0);
        stat.get(2).put("PHP", 0);
        stat.get(2).put("C#", 0);
        stat.get(2).put("Python", 0);
        stat.get(2).put("JavaScript", 0);
        stat.get(2).put("Kotlin", 0);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String name = request.getParameter("username");
        String surname = request.getParameter("usersurname");
        String age = request.getParameter("userage");
        String gender = request.getParameter("gender");
        String englishProficiency = request.getParameter("English proficiency");
        String programmingLanguage = request.getParameter("Programming language");

        Map<String, Integer> map1 = stat.get(1);
        Integer counter1 = map1.get(englishProficiency);
        if (counter1 != null) {
            map1.put(englishProficiency, counter1 + 1);
        }

        Map<String, Integer> map2 = stat.get(2);
        Integer counter2 = map2.get(programmingLanguage);
        if (counter2!=null) {
            map2.put(programmingLanguage, counter2 + 1);
        }


        try {
            writer.println("<p>Name: " + name + "</p>");
            writer.println("<p>Surname: " + surname + "</p>");
            writer.println("<p>Age: " + age + "</p>");
            writer.println("<p>Gender: " + gender + "</p>");
            writer.println("<p>English proficiency: " + englishProficiency + "</p>");
            writer.println("<p>Programming language: " + programmingLanguage + "</p>");
            writer.println("");
            writer.println("<p><b>Statistica: </b></p>");
            for (Integer n : stat.keySet()) {
                writer.print("<p>");
                Map<String, Integer> question = stat.get(n);
                for (String key : question.keySet()) {
                    writer.print("<br>");
                    writer.print(key + " - " + question.get(key));
                }
                writer.print("</p>");

            }
        } finally {
            writer.close();
        }
    }
}



