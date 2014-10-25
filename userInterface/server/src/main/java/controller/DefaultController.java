package controller;

import java.util.ArrayList;
import java.util.List;

import model.Content;
import model.component.Component;
import model.component.Text;
import model.component.Tile;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/hello")
public class DefaultController {

	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public String getMovie(@PathVariable String name, ModelMap model) {
 
		model.addAttribute("movie", name);
		return "list";
 
	}
 
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String getDefaultMovie(ModelMap model) {
 
		model.addAttribute("movie", "this is default movie");
		return "list";
 
	}
	
    @RequestMapping(method = RequestMethod.GET, value = "/getMainPage")
    public @ResponseBody Content getMainPage(){
    	Content content = new Content();
    	Content tileContent = new Content();
    	List<Component> components = new ArrayList<Component>();
    	Tile tile1 = new Tile();
    	Text headerText1 = new Text("This is header");
    	Text innerText1 = new Text("This is content This is content This is content \n + "
    							+ "This is content This is content This is content \n + "
    							+ "This is content This is content This is content");
    	tile1.setHeader(headerText1);
    	List<Component> tileComponents1 = new ArrayList<Component>();
    	tileContent.setContent(tileComponents1);
    	tileComponents1.add(innerText1);
    	tile1.setTileContent(tileContent);
    	
    	Tile tile2 = new Tile();
    	Text headerText2 = new Text("This is header");
    	Text innerText2 = new Text("This is content This is content This is content \n + "
    							+ "This is content This is content This is content \n + "
    							+ "This is content This is content This is content");
    	tile2.setHeader(headerText2);
    	List<Component> tileComponents2 = new ArrayList<Component>();
    	tileContent.setContent(tileComponents2);
    	tileComponents2.add(innerText2);
    	tile2.setTileContent(tileContent);
    	components.add(tile1);
    	components.add(tile2);
    	content.setContent(components);
    	return content;
    }
}
