package khu.cloudproject.uwatch.member.domain.repository;

import khu.cloudproject.uwatch.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
