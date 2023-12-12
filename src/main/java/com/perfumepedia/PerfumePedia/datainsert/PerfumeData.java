package com.perfumepedia.PerfumePedia.datainsert;

import com.perfumepedia.PerfumePedia.domain.*;
import com.perfumepedia.PerfumePedia.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.repository.PerfumeRepository;
import com.perfumepedia.PerfumePedia.service.BrandService;
import com.perfumepedia.PerfumePedia.service.PerfumeService;
import com.perfumepedia.PerfumePedia.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfumeData {

//    @Autowired
//    private PerfumeService perfumeService;
//    @Autowired
//    private PerfumeRepository perfumeRepository;
//    @Autowired
//    private WordService wordService;
//    @Autowired
//    private BrandRepository brandRepository;

    private final PerfumeService perfumeService;
    private final PerfumeRepository perfumeRepository;
    private final WordService wordService;
    private final BrandRepository brandRepository;

    @Autowired
    public PerfumeData(
            PerfumeService perfumeService,
            PerfumeRepository perfumeRepository,
            WordService wordService,
            BrandRepository brandRepository) {
        this.perfumeService = perfumeService;
        this.perfumeRepository = perfumeRepository;
        this.wordService = wordService;
        this.brandRepository = brandRepository;
    }




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
//        perfume.setPrice(Integer.parseInt(collect.getPrice()));
        try {
            String priceString = collect.getPrice();
            if (priceString != null && !priceString.isEmpty()) {
                perfume.setPrice(Integer.parseInt(priceString));
            } else {
                // priceString이 null이거나 비어있는 경우에 대한 처리
                // 예: 0 또는 다른 기본 값으로 설정
                perfume.setPrice(0);
            }
        } catch (NumberFormatException e) {
            // 숫자로 변환할 수 없는 경우에 대한 예외 처리
            // 예: 기본 값으로 설정
            perfume.setPrice(0);
        }
        perfume.setUrl(collect.getPerfume_url());
        perfume.setImage(collect.getName(), collect.getImage());
        perfume.setDbDate(collect.getUpdate_at());

        // 엑셀 데이터의 DISCONTINUE 상태가 변화했을 경우
        // setDiscontinue 호출
        if(collect.getDiscontinue().equals("DISCONTINUE")) {
            perfume.setDiscontinue();
        }

        // CollectionForm의 Brand를 가져와 brandName에 저장
        String brandName = collect.getBrand();
        // brandName에 해당하는 값을 데이터베이스에서 찾아옴
        Optional<Brand> optionalBrand = brandRepository.findByName(brandName);
        // 값이 있다면 브렌드객체를 저장
        optionalBrand.ifPresent(perfume::setBrand);

        return perfume;

    }

//    public Perfume collectDataToPerfume(CollectionForm collect) {
//        // 향수 이름을 가져올 때 null 또는 빈 값이면 기본값을 설정
//        String perfumeName = (collect.getName() != null && !collect.getName().isEmpty()) ? collect.getName() : "기본값";
//
//        Perfume perfume = new Perfume(perfumeName);
//
//        // CollectionForm의 데이터를 Perfume 객체에 set
//        try {
//            String priceString = collect.getPrice();
//            int price = (priceString != null && !priceString.isEmpty()) ? Integer.parseInt(priceString) : 0;
//            perfume.setPrice(price);
//        } catch (NumberFormatException e) {
//            // 숫자로 변환할 수 없는 경우에 대한 예외 처리
//            // 예: 기본 값으로 설정
//            perfume.setPrice(0);
//        }
//
//        perfume.setUrl(collect.getPerfume_url());
//        perfume.setImage(perfumeName, collect.getImage());
//        perfume.setDbDate(collect.getUpdate_at());
//        perfume.setDiscontinue();
//
//        // CollectionForm의 Brand를 가져와 brandName에 저장
//        String brandName = collect.getBrand();
//        // brandName에 해당하는 값을 데이터베이스에서 찾아옴
//        Optional<Brand> optionalBrand = brandRepository.findByName(brandName);
//        // 값이 있다면 브렌드객체를 저장
//        optionalBrand.ifPresent(perfume::setBrand);
//
//        return perfume;
//    }


}
