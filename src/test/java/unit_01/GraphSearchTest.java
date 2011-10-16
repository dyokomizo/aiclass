package unit_01;

import static com.google.common.base.Functions.compose;
import static com.google.common.base.Functions.forMap;
import static lib.Aggregates.sum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static unit_01.Strategies.aStar;
import static unit_01.model.Roads.costFunction;
import lombok.val;

import org.junit.Test;

import unit_01.model.City;
import unit_01.model.Road;
import unit_01.model.Romania;

import com.google.common.collect.ImmutableMap;


public class GraphSearchTest {
    Romania r = new Romania();
    Problem<City, Road> problem = new Problem<City, Road>() {
        @Override public City result(City s, Road a) {
            return r.follow(s, a);
        }


        @Override public Iterable<Road> actions(City s) {
            return r.roads(s);
        }
    };


    @Test public void searchUsingBreadthFirst() throws Exception {
        val search = new GraphSearch<City, Road>(problem, Strategies.<City, Road> breadthFirst());
        val solutions = search.from(r.Arad).to(r.Bucharest).iterator();
        assertTrue(solutions.hasNext());
        val i = solutions.next().iterator();
        assertEquals(r.Arad, i.next().getValue());
        assertEquals(r.Sibiu, i.next().getValue());
        assertEquals(r.Fagaras, i.next().getValue());
        assertEquals(r.Bucharest, i.next().getValue());
        assertFalse(i.hasNext());
        assertFalse(solutions.hasNext());
    }


    @Test public void searchUsingUniformCost() throws Exception {
        val cost = compose(sum(costFunction()), Nodes.<Road> edgesFunction());
        val search = new GraphSearch<City, Road>(problem, Strategies.<City, Road> uniformCost(cost));
        val solutions = search.from(r.Arad).to(r.Bucharest).iterator();
        val i = solutions.next().iterator();
        assertEquals(r.Arad, i.next().getValue());
        assertEquals(r.Sibiu, i.next().getValue());
        assertEquals(r.RimnicuVilcea, i.next().getValue());
        assertEquals(r.Pitesti, i.next().getValue());
        assertEquals(r.Bucharest, i.next().getValue());
        assertFalse(i.hasNext());
    }


    @Test public void searchUsingAStar() throws Exception {
        val cost = compose(sum(costFunction()), Nodes.<Road> edgesFunction());
        val straightLineDistanceToBucharest = ImmutableMap.<City, Integer> builder()//
                .put(r.Oradea, 380)//
                .put(r.Zerind, 374)//
                .put(r.Arad, 366)//
                .put(r.Timisoara, 329)//
                .put(r.Lugoj, 244)//
                .put(r.Mehadia, 241)//
                .put(r.Drobeta, 242)//
                .put(r.Craiova, 160)//
                .put(r.Sibiu, 253)//
                .put(r.Fagaras, 176)//
                .put(r.RimnicuVilcea, 193)//
                .put(r.Pitesti, 101)//
                .put(r.Giurgiu, 77)//
                .put(r.Bucharest, 0)//
                .put(r.Urziceni, 80)//
                .put(r.Hirsova, 151)//
                .put(r.Eforie, 161)//
                .put(r.Vaslui, 199)//
                .put(r.Iasi, 226)//
                .put(r.Neamt, 234)//
                .build();
        val distance = compose(forMap(straightLineDistanceToBucharest), Nodes.<City> vertexFunction());
        val search = new GraphSearch<City, Road>(problem, aStar(cost, distance));
        val solutions = search.from(r.Arad).to(r.Bucharest).iterator();
        val i = solutions.next().iterator();
        assertEquals(r.Arad, i.next().getValue());
        assertEquals(r.Sibiu, i.next().getValue());
        assertEquals(r.RimnicuVilcea, i.next().getValue());
        assertEquals(r.Pitesti, i.next().getValue());
        assertEquals(r.Bucharest, i.next().getValue());
        assertFalse(i.hasNext());
    }
}
