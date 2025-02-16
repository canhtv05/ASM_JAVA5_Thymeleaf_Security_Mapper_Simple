package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.dto.request.NSXCreationRequest;
import com.canhtv05.asm_java5.dto.request.NSXUpdateRequest;
import com.canhtv05.asm_java5.dto.response.NSXResponse;
import com.canhtv05.asm_java5.entity.Nsx;
import com.canhtv05.asm_java5.mapper.NSXMapper;
import com.canhtv05.asm_java5.repository.NsxRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class NSXService {

    NsxRepository nsxRepository;

    @Qualifier("NSXMapper")
    NSXMapper nsxMapper;

    public List<NSXResponse> findAll() {
        return nsxRepository.findAll().stream()
                .map(nsxMapper::toNSXSResponse)
                .toList();
    }

    public NSXResponse findById(Integer id) {
        return nsxMapper.toNSXSResponse(nsxRepository.findById(id).orElse(null));
    }

    public void deleteById(Integer id) {
        nsxRepository.deleteById(id);
    }

    public void add(NSXCreationRequest request) {
        Nsx nsx = nsxMapper.toNSX(request);

        if (nsxRepository.existsByMa(request.getMa())) {
            throw new DuplicateKeyException("Duplicate ma: " + request.getMa());
        }

        nsxRepository.save(nsx);
    }

    public void update(NSXUpdateRequest request)  {
        Nsx nsx = nsxMapper.toNSXUpdate(request);

        nsxRepository.save(nsx);
    }
}
