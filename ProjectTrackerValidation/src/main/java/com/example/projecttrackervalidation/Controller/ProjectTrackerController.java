package com.example.projecttrackervalidation.Controller;

import com.example.projecttrackervalidation.Model.ProjectTracker;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/projects")
public class ProjectTrackerController {
    ArrayList<ProjectTracker> projects = new ArrayList<ProjectTracker>();

    @PostMapping("/add")
    public ResponseEntity addProject(@Valid @RequestBody ProjectTracker project, Errors errors){
            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
        projects.add(project);
        return ResponseEntity.status(HttpStatus.OK).body("Project added successfully");
    }




    @GetMapping("/get")
    public ResponseEntity getProjects(){
        return ResponseEntity.status(HttpStatus.OK).body(projects);
    }


    @DeleteMapping("/delete/{index}")
    public ResponseEntity deleteProject(@PathVariable int index){
        projects.remove(index);
        return ResponseEntity.status(HttpStatus.OK).body("Project removed successfully");
    }

    @PutMapping("update/{index}")
    public ResponseEntity updateProject(@PathVariable int index,@Valid @RequestBody ProjectTracker project,Errors errors){
            if (errors.hasErrors()){
                String message = errors.getFieldError().getDefaultMessage();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
            }
        projects.set(index,project);
        return ResponseEntity.status(HttpStatus.OK).body("Project updated successfully");
    }

    @PutMapping("update/status/{index}")
    public ResponseEntity  updateStatus(@PathVariable int index){
        if (projects.get(index).getStatus().equalsIgnoreCase("Not started")) {
            projects.get(index).setStatus("in progress");
            return ResponseEntity.status(HttpStatus.OK).body("Status is set to (in progress)");
        }  if (projects.get(index).getStatus().equalsIgnoreCase("in progress")) {
            projects.get(index).setStatus("Completed");
            return ResponseEntity.status(HttpStatus.OK).body("Status is set (Completed)");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status must be set to (Not started) or (in progress)");

    }

    @GetMapping("/search/{title}")
    public ResponseEntity searchProjectTitle(@PathVariable String title){
        ArrayList<ProjectTracker> titles =new ArrayList<>();
        for (ProjectTracker p : projects) {
            if (p.getTitle().equalsIgnoreCase(title)){
                titles.add(p);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(titles);
    }

    @GetMapping("/search/companyprojects/{company}")
    public ResponseEntity displayProjectsByCompany(@PathVariable String company){
        ArrayList<ProjectTracker> projectsByCompany = new ArrayList<>();
        for (ProjectTracker project: projects) {
            if (project.getCompanyName().equalsIgnoreCase(company)){
                projectsByCompany.add(project);
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(projectsByCompany);

    }

}
