package com.perfumepedia.PerfumePedia.datainsert;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.Perfume;
import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.domain.WordType;
import com.perfumepedia.PerfumePedia.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.repository.PerfumeRepository;
import com.perfumepedia.PerfumePedia.service.BrandService;
import com.perfumepedia.PerfumePedia.service.PerfumeService;
import com.perfumepedia.PerfumePedia.service.WordService;

import java.util.List;
import java.util.Optional;

public class PerfumeData {

    private PerfumeService perfumeService;
    private PerfumeRepository perfumeRepository;
    private WordService wordService;
    private BrandRepository brandRepository;



    public void insertPerfumeAndWordData(CollectionForm collect) {
        Perfume perfume = collectDataToPerfume(collect);
        Long perfumeId = perfumeService.savePerfume(perfume);

        // #splitName 메서드 사용을 위한 WordSplit 객체 생성
        WordSplit wordSplit = new WordSplit();

        perfume = perfumeRepository.findById(perfumeId).orElse(null);

        if (perfume != null) {
            // Perfume name을 쪼개어 word 객체 생성 및 저장 : splitName 메서드 호출
            List<String> perfumeNameParts = wordSplit.splitName(perfume.getName());

            for (String alias : perfumeNameParts) {
                Word word = new Word(alias, collect.getName(), WordType.PERFUME);
                word.setEntity(perfume);
                word.setDbDate(collect.getUpdate_at());

                // Word 객체 저장
                wordService.saveWord(word);
            }
        }
    }


    public Perfume collectDataToPerfume(CollectionForm collect) {

        Perfume perfume = new Perfume(collect.getName());

        // CollectionForm의 데이터를 Perfume 객체에 set
        perfume.setPrice(Integer.parseInt(collect.getPrice()));
        perfume.setUrl(collect.getPerfume_url());
        perfume.setImage(collect.getName(), collect.getImage());
        perfume.setDbDate(collect.getUpdate_at());
        perfume.setDiscontinue();

        // CollectionForm의 Brand를 가져와 brandName에 저장
        String brandName = collect.getBrand();
        // brandName에 해당하는 값을 데이터베이스에서 찾아옴
        Optional<Brand> optionalBrand = brandRepository.findByName(brandName);
        // 값이 있다면 브렌드객체를 저장
        optionalBrand.ifPresent(perfume::setBrand);

        return perfume;

    }

}
