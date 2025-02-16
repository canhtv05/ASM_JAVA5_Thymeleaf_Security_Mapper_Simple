package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.dto.request.DongSpCreationRequest;
import com.canhtv05.asm_java5.dto.request.DongSpUpdateRequest;
import com.canhtv05.asm_java5.dto.response.DongSPResponse;
import com.canhtv05.asm_java5.entity.DongSP;
import com.canhtv05.asm_java5.mapper.DongSpMapper;
import com.canhtv05.asm_java5.repository.DongSPRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DongSPService {

    DongSPRepository dongSPRepository;
    DongSpMapper dongSpMapper;

    public List<DongSPResponse> findAll() {
        return dongSPRepository.findAll().stream()
                .map(dongSpMapper::toDongSPSResponse)
                .toList();
    }

    public DongSPResponse findById(Integer id) {
        return dongSpMapper.toDongSPSResponse(dongSPRepository.findById(id).orElse(null));
    }

    public void deleteById(Integer id) {
        dongSPRepository.deleteById(id);
    }

    public void add(DongSpCreationRequest request) {
        DongSP dongSP = dongSpMapper.toDongSP(request);

        if (dongSPRepository.existsByMa(request.getMa())) {
            throw new DuplicateKeyException("Duplicate ma: " + request.getMa());
        }

        dongSPRepository.save(dongSP);
    }

    public void update(DongSpUpdateRequest request)  {
        DongSP dongSP = dongSpMapper.toDongSPUpdate(request);

        dongSPRepository.save(dongSP);
    }
}
