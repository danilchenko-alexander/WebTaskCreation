package TasksCreation.controller;

import TasksCreation.domain.Task;
import TasksCreation.repository.TaskRepo;
import TasksCreation.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private TaskRepo taskRepo;

    @GetMapping("/")
    public String greeting(Model model) {
        return "main";
    }

    @PostMapping("/addnewtask")
    public String addNewTask(@RequestParam Map<String, String> form, Map<String, Object> model) {
        Task task = new Task();
        String start = form.get("startDate");
        String end = form.get("endDate");

        if (start.isEmpty() || end.isEmpty()){
            model.put("message", "invalid date filling!");
            return "main";
        }
        if (!Validation.isDateValid(start, end)) {
            model.put("message", "invalid date format!");
            return "main";
        }

        Date startDate = Date.valueOf(form.get("startDate"));
        Date endDate = Date.valueOf(form.get("endDate"));

        task.setSummary(form.get("summary"));
        task.setStartDate(startDate);
        task.setEndDate(endDate);
        task.setAssignee(form.get("assignee"));

        taskRepo.save(task);

        return "redirect:/tasks";
    }
}
