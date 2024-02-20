package com.example.MonthlyPaymentCalculator.controller;

import com.example.MonthlyPaymentCalculator.baseApp.Calculate;
import com.example.MonthlyPaymentCalculator.models.Prospect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.example.MonthlyPaymentCalculator.repository.ProspectRepository;

@Controller
public class ProspectController {
    private final ProspectRepository repo;

    @Autowired
    public ProspectController(ProspectRepository repo){
        this.repo=repo;
    }


    //returns the prospect form for creating new prospects
    @GetMapping("/new")
    public String addProspect(Model model) {
        Prospect prospect = new Prospect();

        model.addAttribute("prospect", prospect);

        model.addAttribute("pageTitle", "Create new prospect");

        return "prospect_form";
    }


    //for saving the prospects when added
    @PostMapping("/save")
    public String saveProspect(Prospect tutorial, RedirectAttributes redirectAttributes) {
        try {
            tutorial.setMonthlyPayment(Calculate.getMonthlyPayment(tutorial.getTotalLoan(),tutorial.getInterest(),tutorial.getYears()));
            repo.save(tutorial);

            redirectAttributes.addFlashAttribute("message", "The Prospect has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/";
    }

    //for deleting a prospect

    @GetMapping("/delete/{id}")
    public String deleteProspect(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            repo.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "The Prospect with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/";
    }

    @GetMapping("/")
    public String showUserList(Model model) {
        model.addAttribute("prospects", repo.findAll());
        return "prospects";
    }
}
