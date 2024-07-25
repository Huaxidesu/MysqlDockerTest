package org.example.controller;

import org.example.model.Animal;
import org.example.repository.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
  public List<Animal> getAnimals() {
    List<Animal> animals = animalRepository.findAll();
    animals.forEach(animal -> System.out.println(animal));
    return animals;
  }

  @PutMapping("/{id}")
  public Animal updateAnimal(@PathVariable int id, @RequestBody Animal animalDetails) {
    Optional<Animal> optionalAnimal = animalRepository.findById(id);
    if (optionalAnimal.isPresent()) {
      Animal animal = optionalAnimal.get();
      animal.setName(animalDetails.getName());
      animal.setSpecies(animalDetails.getSpecies());
      animal.setAge(animalDetails.getAge());
      animal.setHealthStatus(animalDetails.getHealthStatus());
      return animalRepository.save(animal);
    } else {
      throw new RuntimeException("Animal not found with id " + id);
    }
  }

  @DeleteMapping("/{id}")
  public void deleteAnimal(@PathVariable int id) {
    animalRepository.deleteById(id);
  }
}
