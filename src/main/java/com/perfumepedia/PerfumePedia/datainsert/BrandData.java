package com.perfumepedia.PerfumePedia.datainsert;

import com.perfumepedia.PerfumePedia.domain.Brand;
import com.perfumepedia.PerfumePedia.domain.Word;
import com.perfumepedia.PerfumePedia.domain.WordType;
import com.perfumepedia.PerfumePedia.repository.BrandRepository;
import com.perfumepedia.PerfumePedia.service.BrandService;
import com.perfumepedia.PerfumePedia.service.WordService;

import java.util.List;

public class BrandData {

    private BrandService brandService;
    private BrandRepository brandRepository;
    private WordService wordService;


    public void insertBrandAndWordData(CollectionForm collect) {
        Brand brand = collectDataToBrand(collect);
        Long brandId = brandService.saveBrand(brand);

        // #splitName 메서드 사용을 위한 WordSplit 객체 생성
        WordSplit wordSplit = new WordSplit();

        brand = brandRepository.findById(brandId).orElse(null);

        if (brand != null) {
            // Brand name을 쪼개어 word 객체 생성 및 저장 : splitName 메서드 호출
            List<String> brandNameParts = wordSplit.splitName(brand.getName());

            for (String alias : brandNameParts) {

                Word word = new Word(alias, collect.getBrand(), WordType.BRAND);
                word.setEntity(brand);
                word.setDbDate(collect.getUpdate_at());

                // Word 객체 저장
                wordService.saveWord(word);
            }
        }
    }


    private Brand collectDataToBrand(CollectionForm collect) {
        Brand brand = new Brand(collect.getBrand());

        // CollectionForm의 데이터를 Brand 객체에 set

        brand.setUrl(collect.getBrand_url());
        brand.setDbDate(collect.getUpdate_at());

        return brand;
    }

}
