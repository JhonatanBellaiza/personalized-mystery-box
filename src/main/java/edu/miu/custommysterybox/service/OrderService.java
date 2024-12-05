package edu.miu.custommysterybox.service;

import edu.miu.custommysterybox.dto.request.GenerateOrderRequestDto;
import edu.miu.custommysterybox.dto.response.GenerateOrderResponseDto;

import java.util.Optional;

public interface OrderService {

    Optional<GenerateOrderResponseDto> generateOrder(GenerateOrderRequestDto generateOrderRequetDto);
}
