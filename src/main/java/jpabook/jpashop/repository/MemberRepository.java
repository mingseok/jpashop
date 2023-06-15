package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class MemberRepository {

    @PersistenceContext
    private EntityManager em;

    // jpa 저장하는 로직
    public void save(Member member) {
        em.persist(member);
    }

    // jpa 단건 조회
    public Member findOne(Long id) {
        return em.find(Member.class, id);
    }

    // jpa 모든 결과 조회
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    // jpa 회원을 조회하는데, 이름에 의해서 조회하는 방법
    public List<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }
}
