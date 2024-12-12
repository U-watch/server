package khu.cloudproject.uwatch.member2.domain.repository;

import khu.cloudproject.uwatch.member2.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

//    Optional<Member> findById(Long memberId);
}
