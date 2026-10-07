package org.yourcompany.yourproject.catalog.infrastructure.http;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yourcompany.yourproject.catalog.application.BrowserShowCaseUseCase;
import org.yourcompany.yourproject.catalog.application.dto.EventOutput;

@RestController
@RequestMapping("/showcase")
public class ShowcaseController {
    private final BrowserShowCaseUseCase browserShowCaseUseCase;

    public ShowcaseController(BrowserShowCaseUseCase browserShowCaseUseCase) {
        this.browserShowCaseUseCase = browserShowCaseUseCase;
    }

    @GetMapping
    public List<EventOutput> browseShowcase() {
        return browserShowCaseUseCase.execute();
    }
}
