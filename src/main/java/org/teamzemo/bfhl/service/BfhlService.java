package org.teamzemo.bfhl.service;

import org.teamzemo.bfhl.dto.BfhlRequest;
import org.teamzemo.bfhl.dto.BfhlResponse;

public interface BfhlService {

    BfhlResponse process(BfhlRequest request);
}
