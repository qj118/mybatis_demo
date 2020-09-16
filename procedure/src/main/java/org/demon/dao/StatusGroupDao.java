package org.demon.dao;

import org.demon.domain.StatusGroup;

import java.util.List;

public interface StatusGroupDao {

    List<StatusGroup> group(String start, String end);
}
