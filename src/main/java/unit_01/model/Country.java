package unit_01.model;

import static com.google.common.base.Preconditions.checkState;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;


public class Country implements Iterable<City> {
    private final Set<City> cities = new LinkedHashSet<City>();
    private final Graph<City, Road> map = new SparseMultigraph<City, Road>();


    protected void road(City from, int cost, City to) {
        final Road path = new Road(cost);
        checkState(map.addEdge(path, from, to), "Duplicated path from %s to %s", from, to);
    }


    protected City newCity(String name) {
        final City city = new City(name);
        checkState(cities.add(city), "Duplicated city %s", name);
        map.addVertex(city);
        return city;
    }


    public Iterable<Road> roads(City city) {
        return map.getIncidentEdges(city);
    }


    public City follow(City city, Road road) {
        return map.getOpposite(city, road);
    }


    @Override public Iterator<City> iterator() {
        return cities.iterator();
    }
}
