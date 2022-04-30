package ir.ebrahimi.phonebook.controller;

import ir.ebrahimi.phonebook.model.PhoneBookRow;
import ir.ebrahimi.phonebook.model.PhoneBookRowModel;
import ir.ebrahimi.phonebook.services.PhoneBookRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/")
public class PhoneBookRowController {
    @Autowired
    private PhoneBookRowService service;

    public PhoneBookRowController(PhoneBookRowService service) {
        super();
        this.service = service;
    }

    @GetMapping(value = {"/", "/index"})
    public String getHomePage(Model model) {
        return "index";
    }

    @GetMapping(value = "/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping(value = "/logout-success")
    public String getLogoutPage(Model model) {
        return "logout";
    }

    @GetMapping(value = "/rows")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getRows(Model model) {
        List<PhoneBookRow> rows = this.service.findAll();
        model.addAttribute("rows", rows);
        return "rows-view";
    }

    @GetMapping(value = "/rows/add")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddGuestForm(Model model) {
        return "row-view";
    }

    @PostMapping(value = "/rows")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ModelAndView addRow(HttpServletRequest request, Model model,
                               @Valid @ModelAttribute PhoneBookRowModel phoneBookRowModel) {
        PhoneBookRow row = this.service.saveRow(phoneBookRowModel);
        model.addAttribute("row", row);
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        return new ModelAndView("redirect:/rows/" + row.getId());
    }

    @GetMapping(value = "/rows/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getRow(Model model, @PathVariable long id) {
        PhoneBookRow row = this.service.getRow(id);
        model.addAttribute("row", row);
        return "row-view";
    }

    @PostMapping(value = "/rows/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String updateGuest(Model model, @PathVariable long id,
                              @Valid @ModelAttribute PhoneBookRowModel phoneBookRowModel) {
        PhoneBookRow row = this.service.updateRow(id, phoneBookRowModel);
        model.addAttribute("row", row);
        model.addAttribute("rowModel", new PhoneBookRowModel());
        return "row-view";
    }
}
