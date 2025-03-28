package com.sotogito.order.view;

import com.sotogito.menu.MenuFunction;
import com.sotogito.menu.model.dto.MenuDto;
import com.sotogito.order.OrderFunction;

public class PrintResultView {
    public void displaySuccessMessage(OrderFunction function) {
        switch (function) {
            case ORDER: System.out.println("주문 등록 성공"); break;
        }
    }

    public void displayFailMessage(OrderFunction function) {
        switch (function) {
            case ORDER: System.out.println("주문 등록 실패"); break;
            case SEARCH: System.out.println("검색 결과가 없습니다."); break;
        }
    }

    public void displaySearchResult(MenuDto result) {
        System.out.println(result);
    }
}
