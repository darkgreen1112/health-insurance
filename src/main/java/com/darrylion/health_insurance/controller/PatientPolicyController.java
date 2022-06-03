package com.darrylion.health_insurance.controller;

import com.darrylion.health_insurance.entity.PatientPolicy;
import com.darrylion.health_insurance.repository.PatientPolicyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class PatientPolicyController {

    private final PatientPolicyRepository repository;

    @GetMapping("/index")
    public String all(Model model) {
        model.addAttribute("patientPolicies", repository.findAll());

        return "index";
    }

    @GetMapping("/add_patient_policy")
    public String addPatientPolicy() {

        return "add_patient_policy";
    }

    @GetMapping("/edit_patient_policy/{id}")
    public String editPatientPolicy(@PathVariable Long id, Model model) {
        model.addAttribute("patientPolicy", repository.findById(id).orElse(new PatientPolicy()));

        return "edit_patient_policy";
    }

    @PostMapping("/patient_policies")
    public String savePatientPolicy(@ModelAttribute PatientPolicy patientPolicy) {
        repository.save(patientPolicy);

        return "redirect:/index";
    }

    @PostMapping("/patient_policies/{id}")
    public String replacePatientPolicy(@ModelAttribute PatientPolicy newPatientPolicy, @PathVariable Long id) {
        repository.findById(id)
                .map(patientPolicy -> {
                    patientPolicy.setLastName(newPatientPolicy.getLastName());
                    patientPolicy.setFirstName(newPatientPolicy.getFirstName());
                    patientPolicy.setPatronymic(newPatientPolicy.getPatronymic());
                    patientPolicy.setBirthday(newPatientPolicy.getBirthday());
                    patientPolicy.setTermPolicy(newPatientPolicy.getTermPolicy());
                    return repository.save(patientPolicy);
                })
                .orElseGet(() -> repository.save(newPatientPolicy));

        return "redirect:/index";
    }

    @GetMapping("/delete_patient_policy/{id}")
    public String deletePatientPolicy(@PathVariable Long id) {
        repository.deleteById(id);

        return "redirect:/index";
    }
}
