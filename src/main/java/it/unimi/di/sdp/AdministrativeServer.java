package it.unimi.di.sdp;

import com.sun.jersey.api.container.httpserver.HttpServerFactory;
import com.sun.net.httpserver.HttpServer;
import it.unimi.di.sdp.Helper.Position;
import it.unimi.di.sdp.Helper.Statistic;
import it.unimi.di.sdp.DataStucture.StatisticsBuffer;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;

@Path("taxi")
public class AdministrativeServer {

    private static final String HOST = "localhost";
    private static final int PORT = 1337;

    private static final StatisticsBuffer statistics = new StatisticsBuffer();

    @GET
    @Produces({"application/json", "application/xml", "text/plain"})
    public Response getTaxisList(){
        SmartCity smartCity = SmartCity.getInstance();
        return Response.ok(smartCity).build();
    }

    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml", "text/plain"})
    public Response addTaxi(Taxi t){
        try {
            Position position = SmartCity.getInstance().add(t);
            return Response.ok(position).build();
        } catch (IllegalArgumentException e) {
            return Response.serverError().build();
        }
    }

    @Path("delete/{id}")
    @DELETE
    @Consumes({"application/json", "application/xml"})
    public Response deleteTaxi(@PathParam("id") int id){
        SmartCity.getInstance().delete(id);
        return Response.ok().build();
    }

    @Path("statistics/add")
    @POST
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml", "text/plain"})
    public Response addStatistics(Statistic s){
        statistics.addStatistics(s, s.getIdTaxi());
        return Response.ok().build();
    }

    @Path("statistics/{id}/{n}")
    @GET
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml", "text/plain"})
    public Response getTaxiStatisticsById(@PathParam("id") int id, @PathParam("n") Integer n){
        Statistic result = statistics.computeTaxiStatistics(id, n);
        return Response.ok(result).build();
    }

    @Path("allStatistics")
    @GET
    @Consumes({"application/json", "application/xml"})
    @Produces({"application/json", "application/xml", "text/plain"})
    public Response getAllStatistics(@QueryParam("t1") Double t1, @QueryParam("t2") Double t2){
        Statistic result = statistics.computeAllTaxiStatistics(t1, t2);
        return Response.ok(result).build();
    }

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServerFactory.create("http://"+HOST+":"+PORT+"/");
        server.start();

        System.out.println("Server running!");
        System.out.println("Server started on: http://"+HOST+":"+PORT);

        System.in.read();

        System.out.println("Stopping server");
        server.stop(0);
        System.out.println("Server stopped");
    }
}
