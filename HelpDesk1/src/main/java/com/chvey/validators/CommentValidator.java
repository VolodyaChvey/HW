package com.chvey.validators;

import com.chvey.dto.CommentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentValidator {
    @Autowired
    private TextValidator textValidator;
    public boolean save(CommentDto commentDto){
        return  commentDto.getText() != null && textValidator.text(commentDto.getText());
    }
}
