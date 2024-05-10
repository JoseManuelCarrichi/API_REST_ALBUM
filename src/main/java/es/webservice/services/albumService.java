package es.webservice.services;
import es.webservice.modelo.Album;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.websocket.server.PathParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

import java.util.ArrayList;
import java.util.List;

@Path("/album")
public class albumService {
	private static List<Album> albums = new ArrayList<Album>() {
		{
			add(new Album("1","Blue Train", "John Coltrane", 56.99));
			add(new Album("2","Jeru", "Gerry Mulligan", 17.99));
			add(new Album("3","Sarah Vaughan and Clifford Brown", "Sarah Vaughan", 39.99));
			
		}
	};
    /**
     * URL: http://localhost:8080/API_REST_ALBUM/api/album
     * @return Responde una lista de Album
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbums() {
        return Response.ok(albums).build();
    }
    
    /**
     * URL: http://localhost:8080/API_REST_ALBUM/api/album/id
     * 
     * @param id String
     * @return Response
     */
    @GET
    @Path("album/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlbumById(@PathParam("id") String id) {
        Album found = null;
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getId().equalsIgnoreCase(id)) {
                found = albums.get(i);
                break;
            }
        }
        if (found == null) {
            return Response.status(Status.BAD_REQUEST).entity("Album not found").build();
        } else {
            return Response.ok(found).build();
        }
    }
    
    /**
     * URL: http://localhost:8080/API_REST_ALBUM/api/album/addAlbum
     * Parameters: {"id":"4","title":"The Modern Sound of Betty Carter", "artist":"Sarah Vaughan","price": 39.99}
     * @param Album
     * @return Response list 
     */
    @POST
    @Path("/addAlbum")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createAlbum(Album newAlbum) {
 
        this.albums.add(newAlbum);
        //return Response.status(Status.CREATED).build();
        return Response.ok(albums).build();
    }
    
    
    /**
     * URL: http://localhost:8080/API_REST_ALBUM/api/album/updateAlbum
     * Parameters {"id":"4","title":"The Modern Sound of Betty Carter", "artist":"Sarah Vaughan","price": 39.99}
     * @param User
     * @return user modified 
     */
    @PUT
    @Path("/updateAlbum")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAlbum(Album albumUpdate) {
        Album found = null; 
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getId().equals(albumUpdate.getId())) {
                found = albums.get(i);
            }
        }
 
        if (found == null) {
            return Response.status(Status.BAD_REQUEST).entity("Album not found").build();
        } else {
        	found.setArtist(albumUpdate.getArtist());
        	found.setTitle(albumUpdate.getTitle());
        	found.setPrice(albumUpdate.getPrice());
        	found.setId(albumUpdate.getId());
            return Response.ok(found).build();
        }
    }
    
    
    /**
     * URL: http://localhost:8080/API_REST_ALBUM/api/album/deleteAlbum/id
     * 
     * @param id
     * @return Response
     */
    @DELETE
    @Path("/deleteAlbum/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAlbum(@PathParam("id") String id) {
        Album found = null;
        for (int i = 0; i < albums.size(); i++) {
            if (albums.get(i).getId().equals(id)) {
                found = albums.get(i);
                albums.remove(found);
            }
        }
 
        if (found == null) {
            return Response.status(Status.BAD_REQUEST).entity("Album not found").build();
        } else {
            return Response.ok(albums).build();
        }
    }
    
}

