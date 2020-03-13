package co.udea.heroes.api.controller;

import co.udea.heroes.api.model.Hero;
import co.udea.heroes.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    private HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar heroe por id", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "El heroe fue encontrado", response = Page.class),
            @ApiResponse(code = 404, message = "No se encontró el heroe"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> getHero(@PathVariable int id){
        log.info("RESTapi: Buscar heroe por id");
        return ResponseEntity.ok(heroService.getHero(id));
    }

    @GetMapping
    @ApiOperation(value = "Buscar todos los heroes", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Los heroes fueron encontrados", response = Page.class),
            @ApiResponse(code = 404, message = "No se encontraron los héroes"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> getHeroes(){
        log.info("RESTapi: Buscar todos los heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    @GetMapping("search/{term}")
    @ApiOperation(value = "Buscar heroe(s) que contenga(n) el termino", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe(s) encontrado(s)", response = Page.class),
            @ApiResponse(code = 404, message = "No se encontraron los héroes"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<List<Hero>> searchHeroes(@PathVariable String term){
        log.info("RESTapi: Buscar heroe(s) que contenga(n) el termino");
        return ResponseEntity.ok(heroService.searchHeroes(term));
    }

    @GetMapping("name/{name}")
    @ApiOperation(value = "Buscar heroe por nombre", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe encontrado", response = Page.class),
            @ApiResponse(code = 404, message = "No se encontró el heroe"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> findHeroByName(@PathVariable("name") String name){
        log.info("RESTapi: Buscar heroe por nombre");
        return ResponseEntity.ok(heroService.findHeroByName(name));
    }

    @PostMapping
    @ApiOperation(value = "Añadir un heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe añadido", response = Page.class),
            @ApiResponse(code = 409, message = "El heroe ya existe"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        log.info("RESTapi: Añadir un heroe");
        return ResponseEntity.ok(heroService.addHero(hero));
    }

    @DeleteMapping
    @ApiOperation(value = "Eliminar un heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe eliminado", response = Page.class),
            @ApiResponse(code = 409, message = "No se pudo eliminar el heroe"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> deleteHero(int id){
        log.info("RESTapi: Eliminar un heroe");
        return ResponseEntity.ok(heroService.deleteHero(id));
    }

    @PutMapping
    @ApiOperation(value = "Actualizar un heroe", response = Page.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Heroe actualizado", response = Page.class),
            @ApiResponse(code = 409, message = "El heroe ya existe"),
            @ApiResponse(code = 500, message = "Error interno al procesar la respuesta")})
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero){
        log.info("RESTapi: Actualizar un heroe");
        return ResponseEntity.ok(heroService.updateHero(hero));
    }
}
