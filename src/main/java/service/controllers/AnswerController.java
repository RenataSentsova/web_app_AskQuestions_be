package service.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.forms.AnswerForm;
import service.models.Question;
import service.response.ResponseMessage;
import service.services.*;
import service.transfer.AnswerDto;

import javax.validation.ValidationException;
import java.util.List;

import static service.transfer.AnswerDto.from;

@AllArgsConstructor
@CrossOrigin
@RestController
public class AnswerController {
    @Autowired
    private QuestionService questionService;
    @Autowired
    private AnswerService answerService;

    @GetMapping("/answersCount")
    public int findCount(){
        return answerService.findAll().size();
    }

    @GetMapping("/answers/byid/{answerid}")
    public AnswerDto findOne(@PathVariable Long answerid){
        return from(answerService.findOne(answerid));
    }

    @GetMapping("/answers/byquestion/{questionid}")
    public List<AnswerDto> findAllByQuestion(@PathVariable Long questionid){
        Question question = questionService.findOne(questionid);
        return from(answerService.findAllByQuestion(question));
    }

    @PostMapping("/addanswer")
    public ResponseEntity<?> addAnswer(@RequestBody AnswerForm answerForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        answerService.save(answerForm);
        return new ResponseEntity<>(new ResponseMessage("Adding an answer was successful"), HttpStatus.OK);
    }

    @DeleteMapping("/editor/deleteanswer/{id}")
    public void delete(@PathVariable Long id){
        answerService.delete(id);
    }

    @PostMapping("/best")
    public ResponseEntity<?> changeBest(@RequestBody AnswerForm answerForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            throw new ValidationException();
        }
        answerService.changeBest(answerForm);
        return new ResponseEntity<>(new ResponseMessage("Successful answer status change"), HttpStatus.OK);
    }
}
