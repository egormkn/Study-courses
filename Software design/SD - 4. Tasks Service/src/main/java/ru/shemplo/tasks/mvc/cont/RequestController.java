package ru.shemplo.tasks.mvc.cont;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import ru.shemplo.tasks.mvc.model.ListOfTasks;
import ru.shemplo.tasks.mvc.model.Task;

@Controller
public class RequestController {

    @GetMapping (path = "/lists")
    public ModelAndView handleTaskLists () {
        ModelAndView mav = new ModelAndView ("lists");
        
        List <ListOfTasks> lists = new ArrayList <> ();
        lists.add (new ListOfTasks (1, "Uni tasks", 
            Arrays.asList (new Task ("test", 0)
        )));
        mav.addObject ("listsOfTasks", lists);
        return mav;
    }
    
}