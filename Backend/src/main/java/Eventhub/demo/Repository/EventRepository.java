package Eventhub.demo.Repository;

import Eventhub.demo.Entity.Event;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface EventRepository{
     List<Event> events = new ArrayList<>();

}
