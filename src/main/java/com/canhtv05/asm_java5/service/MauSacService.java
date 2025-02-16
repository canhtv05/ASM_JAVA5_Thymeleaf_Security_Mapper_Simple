package com.canhtv05.asm_java5.service;

import com.canhtv05.asm_java5.dto.request.MauSacCreationRequest;
import com.canhtv05.asm_java5.dto.request.MauSacUpdateRequest;
import com.canhtv05.asm_java5.dto.response.MauSacResponse;
import com.canhtv05.asm_java5.entity.MauSac;
import com.canhtv05.asm_java5.mapper.MauSacMapper;
import com.canhtv05.asm_java5.repository.MauSacRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MauSacService {

    MauSacRepository mauSacRepository;
    MauSacMapper mauSacMapper;

    public List<MauSacResponse> findAll() {
        return mauSacRepository.findAll().stream()
                .map(mauSacMapper::toMauSacResponse)
                .toList();
    }

    public MauSacResponse findById(Integer id) {
        return mauSacMapper.toMauSacResponse(mauSacRepository.findById(id).orElse(null));
    }

    public void deleteById(Integer id) {
        mauSacRepository.deleteById(id);
    }

    public void add(MauSacCreationRequest request) {
        MauSac mauSac = mauSacMapper.toMauSac(request);

        if (mauSacRepository.existsByMa(mauSac.getMa())) {
            throw new DuplicateKeyException("Duplicate ma: " + mauSac.getMa());
        }

        mauSacRepository.save(mauSac);
    }

    public void update(MauSacUpdateRequest request) {
        MauSac mauSac = mauSacMapper.toMauSacUpdate(request);

        mauSacRepository.save(mauSac);
    }
}
