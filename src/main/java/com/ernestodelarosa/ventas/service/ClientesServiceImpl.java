package com.ernestodelarosa.ventas.service;

import com.ernestodelarosa.ventas.entity.Clientes;
import com.ernestodelarosa.ventas.exception.ResourceNotFoundException;
import com.ernestodelarosa.ventas.repository.ClientesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientesServiceImpl implements ClientesService {

    private final ClientesRepository clientesRepository;

    @Override
    public List<Clientes> findAll() {
        return clientesRepository.findAll();
    }

    @Override
    public Clientes findById(Integer dpiCliente) {
        return clientesRepository.findByDpiCliente(dpiCliente)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado con DPI: " + dpiCliente));
    }

    @Override
    @Transactional
    public Clientes save(Clientes clientes) {
        if (clientesRepository.existsByDpiCliente(clientes.getDpiCliente())) {
            throw new ResourceNotFoundException("Ya existe un cliente con DPI: " + clientes.getDpiCliente());
        }
        return clientesRepository.save(clientes);
    }

    @Override
    @Transactional
    public Clientes update(Integer dpiCliente, Clientes clientesDetails) {
        Clientes clientes = findById(dpiCliente);

        clientes.setNombreCliente(clientesDetails.getNombreCliente());
        clientes.setApellidoCliente(clientesDetails.getApellidoCliente());
        clientes.setDireccion(clientesDetails.getDireccion());
        clientes.setEstado(clientesDetails.getEstado());

        return clientesRepository.save(clientes);
    }

    @Override
    @Transactional
    public void delete(Integer dpiCliente) {
        Clientes clientes = findById(dpiCliente);
        clientesRepository.delete(clientes);
    }
}