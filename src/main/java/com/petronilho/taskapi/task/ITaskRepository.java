package com.petronilho.taskapi.task;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by renanpetronilho on 22/07/17.
 */
public interface ITaskRepository extends JpaRepository<Task, String>{
}
