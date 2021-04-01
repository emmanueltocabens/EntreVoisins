package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    @Test
    public void toggleFavoriteOnWithSuccess(){
        List<Neighbour> allNeighbours = service.getNeighbours();
        Neighbour nb = allNeighbours.get(0);
        nb.setIsFavorite(false);
        service.toggleFavorite(nb);
        assertTrue(nb.isFavorite());
    }

    @Test
    public void toggleFavoriteOffWithSuccess(){
        List<Neighbour> allNeighbours = service.getNeighbours();
        Neighbour nb = allNeighbours.get(0);
        nb.setIsFavorite(true);
        service.toggleFavorite(nb);
        assertFalse(nb.isFavorite());
    }

    @Test
    public void getFavoriteNeighboursWithSuccess() {
        Neighbour nb1 = service.getNeighbours().get(0);
        nb1.setIsFavorite(true);
        List<Neighbour> favNeighboursList = service.getFavoriteNeighbours();
        assertTrue(favNeighboursList.contains(nb1));
        service.toggleFavorite(nb1);
        favNeighboursList = service.getFavoriteNeighbours();
        assertFalse(favNeighboursList.contains(nb1));
    }


}
