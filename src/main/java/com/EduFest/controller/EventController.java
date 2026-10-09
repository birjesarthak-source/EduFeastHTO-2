
package com.EduFest.controller;

import com.EduFest.entity.Event;
import com.EduFest.entity.EventRoomAllocation;
import com.EduFest.repository.EventRoomAllocationRepository;
import com.EduFest.service.EventAllocationService;
import com.EduFest.service.EventService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.Duration;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final EventAllocationService allocationService;
    private final EventRoomAllocationRepository allocationRepository;

    public EventController(
            EventService eventService,
            EventAllocationService allocationService,
            EventRoomAllocationRepository allocationRepository) {

        this.eventService = eventService;
        this.allocationService = allocationService;
        this.allocationRepository = allocationRepository;
    }

    // ==========================================
    // SHOW EVENTS PAGE
    // ==========================================
    @GetMapping
    public String eventsPage(Model model) {

        model.addAttribute("events", eventService.findAll());
        model.addAttribute("event", new Event());

        return "events";
    }

    // ==========================================
    // SAVE NEW EVENT
    // ==========================================
    @PostMapping
    public String saveEvent(
            @ModelAttribute Event event,
            RedirectAttributes redirectAttributes) {

        try {
            // Newly created events wait for allocation
            event.setStatus("PLANNED");

            eventService.save(event);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Event created successfully. Click 'Auto Allocate All' to allocate rooms."
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Unable to save event: " + e.getMessage()
            );
        }

        return "redirect:/events";
    }

    // ==========================================
    // ALLOCATE SINGLE EVENT
    // ==========================================
    @PostMapping("/{id}/allocate")
    public String allocateSingleEvent(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        try {
            Event event = eventService.get(id);

            allocationService.allocate(event);

            redirectAttributes.addFlashAttribute(
                    "success",
                    "Rooms allocated successfully for " + event.getEventName()
            );

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    e.getMessage()
            );
        }

        return "redirect:/events";
    }

    // ==========================================
    // AUTO ALLOCATE ALL EVENTS
    // ==========================================
    @PostMapping("/allocate-all")
    public String allocateAllEvents(
            RedirectAttributes redirectAttributes) {

        try {
            EventAllocationService.BatchAllocationResult result =
                    allocationService.allocateAll();

            redirectAttributes.addFlashAttribute(
                    "allocationResults",
                    result.getResults()
            );

            redirectAttributes.addFlashAttribute(
                    "successCount",
                    result.getSuccessCount()
            );

            redirectAttributes.addFlashAttribute(
                    "failureCount",
                    result.getFailureCount()
            );

            if (result.getFailureCount() == 0) {

                redirectAttributes.addFlashAttribute(
                        "success",
                        "All " + result.getSuccessCount()
                                + " planned events were allocated successfully."
                );

            } else {

                redirectAttributes.addFlashAttribute(
                        "success",
                        result.getSuccessCount()
                                + " event(s) allocated successfully. "
                                + result.getFailureCount()
                                + " event(s) could not be allocated."
                );
            }

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Auto allocation failed: " + e.getMessage()
            );
        }

        return "redirect:/events";
    }

    // ==========================================
    // VIEW ALLOCATED ROOMS FOR AN EVENT
    // ==========================================
    @GetMapping("/{id}/view")
    public String viewAllocation(
            @PathVariable Long id,
            Model model,
            RedirectAttributes redirectAttributes) {

        try {
            Event event = eventService.get(id);

            List<EventRoomAllocation> allocations =
                    allocationRepository.findByEvent(event);

            model.addAttribute("event", event);
            model.addAttribute("allocations", allocations);

            long durationMinutes = Duration.between(
                    event.getStartTime(),
                    event.getEndTime()
            ).toMinutes();

            model.addAttribute("durationMinutes", durationMinutes);

            return "event-allocation-details";

        } catch (Exception e) {

            redirectAttributes.addFlashAttribute(
                    "error",
                    "Unable to view allocation: " + e.getMessage()
            );

            return "redirect:/events";
        }
    }
}
