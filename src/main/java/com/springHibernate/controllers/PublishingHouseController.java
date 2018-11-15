package com.springHibernate.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springHibernate.models.PublishingHouse;
import com.springHibernate.services.PublishingHouseService;

@Controller
public class PublishingHouseController {

	@Autowired
	private PublishingHouseService houseService;

	@GetMapping("/house")
	public String index(Model model) {
		model.addAttribute("phouses", houseService.getAll());
		return "house_list";
	}

	@GetMapping("/house/{id}/delete")
	public String delete(@PathVariable("id") Integer id, RedirectAttributes redirect) {
		houseService.remove(id);
		redirect.addFlashAttribute("success", new StringBuilder().append("Row deleted ").append(id).toString());
		return "redirect:/house";
	}

	@GetMapping("/house/add")
	public String add(Model model) {
		model.addAttribute("phouse", new PublishingHouse());
		return "house_form";
	}

	@GetMapping("/house/{id}/edit")
	public String edit(@PathVariable("id") Integer id, Model model) {
		model.addAttribute("phouse", houseService.getById(id));
		return "house_form";
	}

	@PostMapping("/house/save")
	public String saveEdited(@Valid PublishingHouse house, Model model, RedirectAttributes redirect) {
		houseService.save(house);
		redirect.addFlashAttribute("success", "Edited");
		return "redirect:/house";
	}

}
