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
     * parse neighbours list to find a specific neighbour. returns null if not found.
     * @param neighbour
     * @return neighbour if found or null if not found
     */
    public Neighbour getNeighbour(Neighbour neighbour) {
        for (Neighbour tmp : neighbours) {
            if (tmp.equals(neighbour)) {
                return tmp;
            }
        }
        return null;
    }

    /**
     * sets isFavorite of given neighbour to false if it was true, or true if it was false
      * @param neighbour the one we want to edit
     * @return neighbour the object after edit
     */
    public Neighbour toggleFavorite(Neighbour neighbour){
        Neighbour temp = getNeighbour(neighbour);
        if(temp.isFavorite()){
            temp.setIsFavorite(false);
        } else {
            temp.setIsFavorite(true);
        }
        return temp;
    }

    /**
     * returns a list of the user's favorite neighbours
     * @return list of favorite neighbours
     */
    public ArrayList<Neighbour> getFavoriteNeighbours() {
        ArrayList<Neighbour> favoriteNeighbours = new ArrayList<>();
        for(Neighbour temp : neighbours){
            if(temp.isFavorite()){
                favoriteNeighbours.add(temp);
            }
        }
        return favoriteNeighbours;
    }
}
