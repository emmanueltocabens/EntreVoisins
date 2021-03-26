package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.List;


/**
 * Neighbour API client
 */
public interface NeighbourApiService {

    /**
     * Get all my Neighbours
     * @return {@link List}
     */
    List<Neighbour> getNeighbours();

    /**
     * Deletes a neighbour
     * @param neighbour
     */
    void deleteNeighbour(Neighbour neighbour);

    /**
     * Create a neighbour
     * @param neighbour
     */
    void createNeighbour(Neighbour neighbour);

    /**
     * sets isFavorite of given neighbour to false if it was true, or true if it was false
     * @param nb neighbour
     */
    void toggleFavorite(Neighbour nb);

    /**
     * returns a list of the user's favorite neighbours
     * @return list of favorite neighbours
     */
    List<Neighbour> getFavoriteNeighbours();
}
