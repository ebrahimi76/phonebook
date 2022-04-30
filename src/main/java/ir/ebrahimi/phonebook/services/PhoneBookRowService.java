package ir.ebrahimi.phonebook.services;

import ir.ebrahimi.phonebook.exception.InfoNotFoundException;
import ir.ebrahimi.phonebook.model.PhoneBookRow;
import ir.ebrahimi.phonebook.model.PhoneBookRowModel;
import ir.ebrahimi.phonebook.repository.PhoneBookRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PhoneBookRowService {
    @Autowired
    private  PhoneBookRowRepository repository;

    public PhoneBookRowService(PhoneBookRowRepository repository) {
        super();
        this.repository = repository;
    }

    @Transactional
    public List<PhoneBookRow> findAll(){
        return this.repository.findAll();
    }

    @Transactional
    public PhoneBookRow saveRow(PhoneBookRowModel model){
        return this.repository.save(model.translateModelToEntity());
    }

    @Transactional
    public PhoneBookRow getRow(long id){
        Optional<PhoneBookRow> row = this.repository.findById(id);
        if (row.isPresent()) {
            return row.get();
        }
        throw new InfoNotFoundException("Info not found with id: " + id);
    }

    @Transactional
    public PhoneBookRow updateRow(long id,PhoneBookRowModel model){
        Optional<PhoneBookRow> existing = this.repository.findById(id);
        if (existing.isEmpty()) {
            throw new InfoNotFoundException("Info not found with id: " + id);
        }
        PhoneBookRow row = model.translateModelToEntity();
        row.setId(id);
        return this.repository.save(row);
    }
}
