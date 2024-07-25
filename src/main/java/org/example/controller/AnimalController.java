package org.example.controller;

import org.example.model.Animal;
import org.example.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/animals")
public class AnimalController {

  @Autowired
  private AnimalRepository animalRepository;

  @PostMapping
  public Animal addAnimal(@RequestBody Animal animal) {
    return animalRepository.save(animal);
  }

  @GetMapping
  public List<Animal> getAllAnimals() {
    return animalRepository.findAll();
  }

  @PutMapping("/{id}")
  public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animalDetails) {
    Animal animal = animalRepository.findById(id).orElseThrow();
    animal.setName(animalDetails.getName());
    animal.setSpecies(animalDetails.getSpecies());
    animal.setAge(animalDetails.getAge());
    animal.setHealthStatus(animalDetails.getHealthStatus());
    return animalRepository.save(animal);
  }

  @DeleteMapping("/{id}")
  public void deleteAnimal(@PathVariable int id) {
    Animal animal = animalRepository.findById(id).orElseThrow();
    animalRepository.delete(animal);
  }
}
