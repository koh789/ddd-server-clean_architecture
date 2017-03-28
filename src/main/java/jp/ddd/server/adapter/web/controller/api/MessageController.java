package jp.ddd.server.adapter.web.controller.api;

import jp.ddd.server.adapter.web.controller.BaseApi;
import jp.ddd.server.adapter.web.controller.input.message.MessageForm;
import jp.ddd.server.adapter.web.presenter.api.output.ResultJson;
import jp.ddd.server.adapter.web.presenter.api.output.message.RegisteredMessageJson;
import jp.ddd.server.other.exception.AccessPermissonException;
import jp.ddd.server.other.exception.AuthException;
import jp.ddd.server.other.utils.Cookies;
import jp.ddd.server.other.utils.Msg;
import jp.ddd.server.usecase.web.inputport.MessageUseCase;
import jp.ddd.server.usecase.web.inputport.RoomUseCase;
import jp.ddd.server.usecase.web.inputport.UserUseCase;
import jp.ddd.server.usecase.web.outputport.MessagePresenter;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by noguchi_kohei 
 */
@RestController
@RequestMapping(value = "/api/v1/rooms/{roomId}/messages", produces = MediaType.APPLICATION_JSON_VALUE)
public class MessageController extends BaseApi {
    @Autowired
    private UserUseCase userUseCase;
    @Autowired
    private RoomUseCase roomUseCase;
    @Autowired
    private MessageUseCase messageUseCase;
    @Autowired
    private MessagePresenter messagePresenter;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResultJson<RegisteredMessageJson> register(HttpServletRequest req, @PathVariable("roomId") Integer roomId,
      @RequestBody @Validated MessageForm form) {
        val user = userUseCase.getOptBySid(Cookies.getKey(req)).orElseThrow(() -> new AuthException());

        if (roomUseCase.isRoomUser(roomId, user.getUserId().getId())) {
            throw new AccessPermissonException(Msg.FORBIDDEN_ROOM, true);
        }
        if (user.getUserId().isNotEquals(form.getUserId())) {
            throw new AccessPermissonException(Msg.FORBIDDEN_MSG_POST, true);
        }
        return messagePresenter.toRegisteredJson(messageUseCase.register(roomId, form.getUserId(), form.getContent()));
    }

    //    //
    //    @RequestMapping(value = "", method = RequestMethod.GET)
    //    public ResultJson<ImmutableList<MessagesJson>> get(HttpServletRequest req, @PathVariable("roomId") Integer roomId,
    //      @RequestParam(value = "p", defaultValue = "1") int pageNum) {
    //        val user = SessionUser.getOpt(sessionUserRedisGateway, Cookies.getKey(req))
    //          .orElseThrow(() -> new AuthException());
    //
    //        val dtos = messageService.find(roomId, user.getUserId(), Page.create(pageNum, 50));
    //
    //        return ResultJson.create(dtos.collect(dto -> MessagesJson.create(dto)));
    //    }
}
