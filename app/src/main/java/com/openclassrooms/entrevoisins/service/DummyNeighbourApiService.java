package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.model.Neighbour;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements  NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }

    /**
     * sets isFavorite of given neighbour to false if it was true, or true if it was false
      * @param nb neighbour that must be edited
     * @return neighbour the object after edit
     */
    public void toggleFavorite(Neighbour nb){
        Neighbour temp = getNeighbours().get(neighbours.indexOf(nb));
        temp.setIsFavorite(!temp.isFavorite());
    }

    /**
     * returns a list of the user's favorite neighbours
     * @return list of favorite neighbours
     */
    public ArrayList<Neighbour> getFavoriteNeighbours() {
        ArrayList<Neighbour> favoriteNeighbours = new ArrayList<>();
        for(Neighbour temp : neighbours){
            if(temp.isFavorite())
                favoriteNeighbours.add(temp);
        }
        return favoriteNeighbours;
    }
}
