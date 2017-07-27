package com.petronilho.taskapi.task;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.ConstraintViolation;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Created by renanpetronilho on 22/07/17.
 */
@RestController
public class TaskController {

    @Autowired
    private ITaskRepository repository;


    @Autowired
    private LocalValidatorFactoryBean validatorFactory;

    @RequestMapping(value = "/tasks", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@RequestBody Task task, UriComponentsBuilder ucBuilder) {
        if(Objects.nonNull(task.getId())) {
            Task currentTask = repository.findOne(task.getId());

            if (Objects.nonNull(currentTask)) {
                return new ResponseEntity<>(HttpStatus.CONFLICT);
            }

        }

        validate(task);

        repository.save(task);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/tasks/{id}").buildAndExpand(task.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Task> update(@PathVariable("id") String id, @RequestBody Task task) {
        Task currentTask = repository.findOne(id);

        if (Objects.isNull(task)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        task.setId(id);
        repository.save(task);

        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id) {
        Task task = repository.findOne(id);

        if (Objects.isNull(task)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        repository.delete(task);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<Task>> listAll(Pageable pageable) {
        List<Task> tasks = repository.findAll(pageable).getContent();

        if (tasks.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }


    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Task> get(@PathVariable("id") String id) {
        Task task = repository.findOne(id);

        if(Objects.isNull(task)){
           return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    private void validate(Task task) {
        Set<ConstraintViolation<Task>> errors = validatorFactory.validate(task);
        if(!errors.isEmpty()) {
            throw new TaskException(errors);
        }
    }

}
