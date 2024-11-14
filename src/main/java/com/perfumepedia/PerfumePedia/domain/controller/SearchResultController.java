//package com.perfumepedia.PerfumePedia.domain.controller;
//
//import com.perfumepedia.PerfumePedia.domain.dto.SearchResultDto;
//import com.perfumepedia.PerfumePedia.global.enums.NoneResponse;
//import com.perfumepedia.PerfumePedia.global.response.Response;
//import com.perfumepedia.PerfumePedia.global.response.SuccessResponse;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import static com.perfumepedia.PerfumePedia.global.enums.SuccessCode.SUCCESS;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping("/api")
//public class SearchResultController {
//
//
////    private fianl WordService WordService;
////
////    @Autowired
////    public SearchResultController(WordService wordService){
////        this.wordService=wordService;
////    }
//
//    SearchResultDto searchResultDto ;
//
//    @GetMapping("search")
//    public ResponseEntity<Response<NoneResponse>> getSearchResultDto(){
//        SuccessResponse<NoneResponse> response = new SuccessResponse<>(SUCCESS, NoneResponse.NONE);
//        return Response.success(response);
//    }
//
//    @GetMapping("search/advanced")
//    public PerfumeDetailDto getPerfumeDetailDto(){
//
//        return perfumeDetailDto;
//    }
//
//    @GetMapping("favperfume")
//    public SearchResultDto getFavPerfumeResultDto(){
//
//        return searchResultDto;
//    }
//
//    @GetMapping("favperfume/advanced")
//    public PerfumeDetailDto getFavPerfumeDetailDto(){
//
//        return perfumeDetailDto;
//    }
//
//
//}
