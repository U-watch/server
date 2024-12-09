package khu.cloudproject.uwatch.member.domain.repository;

import khu.cloudproject.uwatch.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

//    Optional<Member> findById(Long memberId);
}
