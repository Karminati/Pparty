package br.cefet.pparty.service;

import br.cefet.pparty.dao.AvaliacaoDao;
import br.cefet.pparty.dao.ChatDao;
import br.cefet.pparty.model.Chat;

import java.util.List;
import org.jdbi.v3.core.Jdbi;
import org.springframework.stereotype.Service;

@Service
public class ChatService {
    
    private final ChatDao chatDao;

        public ChatService(Jdbi jdbi)
    {
        this.chatDao = jdbi.onDemand(ChatDao.class);

    }

    public Chat inserir (Chat chat){
        int idChat = chatDao.insert(chat);
        chat.setIdChat(idChat);
        return chat; 
    }

    public Chat consultarPorId (int idChat){
        return chatDao.get(idChat);
    }

    public List<Chat> consultarPorIdUsuario (int idUsuario){
        return chatDao.getAllByIdUsuario(idUsuario);
    }
}
