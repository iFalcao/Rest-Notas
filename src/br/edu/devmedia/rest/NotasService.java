package br.edu.devmedia.rest;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.edu.devmedia.dao.NotaDAO;
import br.edu.devmedia.entidade.Nota;

// Caminho para qual esse serviço será utilizado, ou seja, a URL que define que essa classe será chamada
@Path("/notas")
public class NotasService {
	
	private static final String CHARSET_UTF8 = ";charset=utf-8;";
	
	private NotaDAO notaDAO;
	
	// Construtor desse Objeto é da própria biblioteca do JERSEY, assim não é necessário criar um construtor
	@PostConstruct
	private void iniciar() {
		notaDAO = new NotaDAO();
	}
	
	
	@GET
	@Path("/lista")
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public List<Nota> listarNotas() {
		
		List<Nota> lista = null;
		
		try {
			lista = notaDAO.listarNotas();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return lista;
	}
	
	
	@GET
	@Path("/get/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	public Nota buscarNotaPeloID(@PathParam("id") int idNota) {
		
		Nota nota = null;
		
		try {
			nota = notaDAO.buscarNotaPeloId(idNota);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return nota;
	}
	
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String adicionarNota(Nota nota) {
		
		String msg = "";
		System.out.println(nota.getTitulo());
		
		try {
			int idGerado = notaDAO.adicionarNota(nota);
			msg = String.valueOf(idGerado);
		} catch (Exception e) {
			msg = "Erro ao adicionar nota";
			e.printStackTrace();
		}
		return msg;
	}
	
	
	@PUT
	@Path("/edit/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String editarNota(Nota nota, @PathParam("id") int index) {
		String msg = "";
		
		try {
			notaDAO.editarNota(nota, index);
			msg = "Nota foi editada com sucesso";
			
		} catch(Exception e) {
			
			msg = "Erro ao editar nota";
			e.printStackTrace();
		}
		return msg;
	}
	
	
	@DELETE
	@Path("/delete/{id}")
	@Consumes(MediaType.APPLICATION_JSON + CHARSET_UTF8)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletarNota(@PathParam("id") int index) {
		String msg = "";
		
		try {
			notaDAO.deletarNota(index);
			msg = "Nota deletada com sucesso";
		} catch (Exception e) {
			msg = "Erro ao adicionar nota";
			e.printStackTrace();
		}
		
		return msg;
	}
}
