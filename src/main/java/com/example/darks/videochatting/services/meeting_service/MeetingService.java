package com.example.darks.videochatting.services.meeting_service;

import com.example.darks.videochatting.exceptions.MeetingNotFoundException;
import com.example.darks.videochatting.models.Meeting;
import com.example.darks.videochatting.models.User;
import com.example.darks.videochatting.repositories.MeetingRepository;
import com.example.darks.videochatting.services.user_service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MeetingService implements IMeetingService{

    private final IUserService userService;
    private final MeetingRepository meetingRepository;

    @Override
    public Meeting getMeetingByReceiverId(UUID accepterId) {
        return meetingRepository.findByReceiver_Id(accepterId)
                .orElseThrow(() -> new MeetingNotFoundException("Meeting not found!"));
    }

    @Override
    public Meeting createMeeting(UUID inviterId, UUID receiverId) {
        Meeting meeting = new Meeting();

        User inviterUser = userService.getUserById(inviterId);
        User receiverUser = userService.getUserById(receiverId);

        meeting.setInviter(inviterUser);
        meeting.setReceiver(receiverUser);

        UUID roomId = UUID.randomUUID();

        meeting.setRoomId(roomId);

        return meetingRepository.save(meeting);
    }

    @Override
    public void deleteMeeting(UUID meetingId) {
        meetingRepository.deleteById(meetingId);
    }
}
