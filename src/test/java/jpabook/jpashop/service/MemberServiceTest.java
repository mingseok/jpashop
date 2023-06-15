package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

@RunWith(SpringRunner.class) // junit 실행할때, 스프링이랑 엮어서 사용하고 싶을때 사용한다.
@SpringBootTest // 스프링부트를 띄운 상태에서 테스트를 진행하고 싶을때 사용한다.
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test // 디비에 잘 들어갔는지, 확인해 보고 싶다면 -> @Rollback(value = false)
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setName("kimmmm");

        Long saveId = memberService.join(member);

        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        memberService.join(member1);
        memberService.join(member2); // 예외가 발생한다.

        fail("예외가 발생해야 한다.");
    }
}