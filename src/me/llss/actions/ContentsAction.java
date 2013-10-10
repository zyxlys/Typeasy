package me.llss.actions;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import me.llss.service.impl.CommentsServiceImpl;
import me.llss.service.impl.ContentsServiceImpl;
import me.llss.service.impl.MetasServiceImpl;
import me.llss.service.impl.OptionsServiceImpl;
import me.llss.service.impl.RelationshipsServiceImpl;
import me.llss.service.impl.UsersServiceImpl;
import me.llss.utils.ContentsComparator;
import me.llss.utils.MetasComparator;
import me.llss.vo.CommentsVO;
import me.llss.vo.ContentsVO;
import me.llss.vo.MetasVO;
import me.llss.vo.OptionsVO;
import me.llss.vo.RelationshipsVO;
import me.llss.vo.UsersVO;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

/**
 * Contents Action
 * 
 * @author Acris
 * @version 2.0 2013/09/21 13:36
 */
public class ContentsAction extends ActionSupport implements Action {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private ContentsServiceImpl cs = new ContentsServiceImpl();
	private CommentsServiceImpl cos = new CommentsServiceImpl();
	private MetasServiceImpl ms = new MetasServiceImpl();
	private OptionsServiceImpl os = new OptionsServiceImpl();
	private UsersServiceImpl us = new UsersServiceImpl();
	private RelationshipsServiceImpl rs = new RelationshipsServiceImpl();

	private String type;
	private Integer authorId;
	private String title;
	private String text;
	private String category;
	private String name;
	private String keywords;
	private Integer mid;
	private Integer cid;
	private Integer oldCMid;
	private Integer[] contentIdArray;

	/*
	 * Getters and setters
	 */

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getAuthorId() {
		return authorId;
	}

	public void setAuthorId(Integer authorId) {
		this.authorId = authorId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public Integer getMid() {
		return mid;
	}

	public void setMid(Integer mid) {
		this.mid = mid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getOldCMid() {
		return oldCMid;
	}

	public void setOldCMid(Integer oldCMid) {
		this.oldCMid = oldCMid;
	}

	public Integer[] getContentIdArray() {
		return contentIdArray;
	}

	public void setContentIdArray(Integer[] contentIdArray) {
		this.contentIdArray = contentIdArray;
	}

	@Override
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}

	/**
	 * 发表文章或页面
	 * 
	 * @return
	 */
	public String add() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		ContentsVO content = new ContentsVO();

		String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		int uid = ((UsersVO) session.getAttribute("user")).getUid();
		content.setCreated(nowtime);
		content.setTitle(title);
		content.setText(text);
		content.setType(type);
		content.setAuthorId(authorId);
		cs.add(content);

		if (type.equals("post")) {
			List<ContentsVO> contentsList = cs.list();
			Collections.sort(contentsList, new ContentsComparator());
			int cid = -1;
			for (int i = (contentsList.size() - 1); i < contentsList.size(); i++) {
				cid = (contentsList.get(i)).getCid();
			}

			int cMid = -1;
			if (category != null)
				cMid = Integer.valueOf(category);

			RelationshipsVO re1 = new RelationshipsVO();
			re1.setCid(cid);
			re1.setMid(cMid);
			rs.add(re1);
			ms.editCount(cMid, "+");

			String nameArray[] = name.split(",");
			RelationshipsVO re2 = new RelationshipsVO();
			MetasVO tMeta = null;

			int num = nameArray.length;
			int tMidArray[] = new int[num];
			int tMid = -1;
			for (int i = 0; i < num; i++) {
				if ((ms.findByName(nameArray[i])).getName() != null) {
					tMidArray[i] = (ms.findByName(nameArray[i])).getMid();
					if (tMidArray[i] != 0) {
						re2.setCid(cid);
						re2.setMid(tMidArray[i]);
						rs.add(re2);
						ms.editCount(tMidArray[i], "+");
					}
				} else {
					tMeta = new MetasVO();
					if (!("".equals(nameArray[i]))) {
						tMeta.setName(nameArray[i]);
						tMeta.setType("tag");
						ms.add(tMeta);
					}
					List<MetasVO> metasList = ms.listAll();
					Collections.sort(metasList, new MetasComparator());

					for (int j = (metasList.size() - 1); j > 0; j--) {
						if ("tag".equals(metasList.get(j).getType())) {
							tMid = (metasList.get(j)).getMid();
							break;
						}
					}
					re2.setCid(cid);
					re2.setMid(tMid);
					rs.add(re2);
					ms.editCount(tMid, "+");
				}
			}
		}
		/* 获取相关session */

		List<RelationshipsVO> relationships = rs.list();
		Collections.reverse(relationships);
		session.setAttribute("relationships", relationships);

		List<ContentsVO> contents = cs.list();
		Collections.reverse(contents);
		session.setAttribute("contents", contents);

		List<CommentsVO> comments = cos.list();
		Collections.reverse(comments);
		session.setAttribute("comments", comments);

		List<UsersVO> users = us.list();
		Collections.reverse(users);
		session.setAttribute("users", users);

		List<MetasVO> metas = ms.listAll();
		Collections.reverse(metas);
		session.setAttribute("metas", metas);

		List<OptionsVO> options = os.list();
		Collections.reverse(options);
		session.setAttribute("options", options);

		if (type.equals("post")) {
			if (authorId == uid) {
				request.setAttribute("message", "发表成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts-visitor.jsp");
				return "visitorAddSuccess";
			} else {
				request.setAttribute("message", "发表成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts.jsp");
				return "addPostSuccess";
			}
		} else {
			request.setAttribute("message", "发表成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-pages.jsp");
			return "addPageSuccess";
		}
	}

	/**
	 * 搜索文章
	 * 
	 * @return
	 */
	public String search() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<ContentsVO> sContents = cs.search(keywords);
		Collections.reverse(sContents);
		HttpSession session = request.getSession();
		session.setAttribute("sContents", sContents);
		return "searchSuccess";
	}

	/**
	 * 按标签查看
	 * 
	 * @return
	 */
	public String listByTag() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<RelationshipsVO> rls = rs.findByMid(mid);
		List<ContentsVO> tmContents = new ArrayList<ContentsVO>();
		for (RelationshipsVO rl : rls) {
			int cid = rl.getCid();
			tmContents.add(cs.find(cid));
		}
		Collections.reverse(tmContents);
		HttpSession session = request.getSession();
		session.setAttribute("tmContents", tmContents);
		return "listByTagSuccess";
	}

	/**
	 * 按目录查看
	 * 
	 * @return
	 */
	public String listByCategory() {
		HttpServletRequest request = ServletActionContext.getRequest();
		List<RelationshipsVO> rls = rs.findByMid(mid);
		List<ContentsVO> cmContents = new ArrayList<ContentsVO>();
		for (RelationshipsVO rl : rls) {
			int cid = rl.getCid();
			cmContents.add(cs.find(cid));
		}

		Collections.reverse(cmContents);
		HttpSession session = request.getSession();
		session.setAttribute("cmContents", cmContents);
		return "listByCategorySuccess";
	}

	/**
	 * 删除文章
	 * 
	 * @return
	 */
	public String del() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		int uid = ((UsersVO) session.getAttribute("user")).getUid();
		List<RelationshipsVO> res = rs.findByCid(cid);
		for (RelationshipsVO re : res) {
			ms.editCount(re.getMid(), "-");
		}
		String type = request.getParameter("type");
		cs.del(cid);
		cos.delByCid(cid);
		rs.del(cid);

		/* 获取相关session */

		List<RelationshipsVO> relationships = rs.list();
		Collections.reverse(relationships);
		session.setAttribute("relationships", relationships);

		List<ContentsVO> contents = cs.list();
		Collections.reverse(contents);
		session.setAttribute("contents", contents);

		List<CommentsVO> comments = cos.list();
		Collections.reverse(comments);
		session.setAttribute("comments", comments);

		List<UsersVO> users = us.list();
		Collections.reverse(users);
		session.setAttribute("users", users);

		List<MetasVO> metas = ms.listAll();
		Collections.reverse(metas);
		session.setAttribute("metas", metas);

		List<OptionsVO> options = os.list();
		Collections.reverse(options);
		session.setAttribute("options", options);

		if (type.equals("post")) {
			if (authorId == uid) {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts-visitor.jsp");
				return "visitorDelSuccess";
			} else {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts.jsp");
				return "delPostSuccess";
			}
		} else {
			request.setAttribute("message", "删除成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-pages.jsp");
			return "delPageSuccess";
		}
	}

	/**
	 * 修改文章
	 * 
	 * @return
	 */
	public String edit() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		ContentsVO c = new ContentsVO();
		String nowtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(Calendar.getInstance().getTime());
		c.setModified(nowtime);
		c.setCid(cid);
		c.setTitle(title);
		c.setText(text);
		c.setType(type);
		cs.edit(c);
		if (authorId == null)
			authorId = -1;
		int uid = ((UsersVO) session.getAttribute("user")).getUid();

		if (type.equals("post")) {

			int cMid = -1;
			if (category != null)
				cMid = Integer.valueOf(category);
			RelationshipsVO re1 = new RelationshipsVO();
			re1.setCid(cid);
			re1.setMid(cMid);
			rs.edit(re1, oldCMid);

			String nameArray[] = name.split(",");
			RelationshipsVO re2 = new RelationshipsVO();
			MetasVO tMeta = null;

			int num = nameArray.length;
			int tMidArray[] = new int[num];
			int tMid = -1;
			for (int i = 0; i < num; i++) {

				if ((ms.findByName(nameArray[i])).getName() != null) {
					tMidArray[i] = (ms.findByName(nameArray[i])).getMid();
					if (tMidArray[i] != 0) {
						re2.setCid(cid);
						re2.setMid(tMidArray[i]);
						rs.add(re2);
						ms.editCount(tMid, "+");
					}
				} else {
					tMeta = new MetasVO();
					if (!("".equals(nameArray[i]))) {
						tMeta.setName(nameArray[i]);
						tMeta.setType("tag");
						ms.add(tMeta);

						List<MetasVO> metasList = ms.listAll();
						Collections.sort(metasList, new MetasComparator());

						for (int j = (metasList.size() - 1); j > 0; j--) {
							if ("tag".equals(metasList.get(j).getType())) {
								tMid = (metasList.get(j)).getMid();
								break;
							}
						}
						re2.setCid(cid);
						re2.setMid(tMid);
						rs.add(re2);
						ms.editCount(tMid, "+");
					}
				}
			}

			List<RelationshipsVO> relationships = rs.list();
			Collections.reverse(relationships);
			session.setAttribute("relationships", relationships);

			List<ContentsVO> contents = cs.list();
			Collections.reverse(contents);
			session.setAttribute("contents", contents);

			List<CommentsVO> comments = cos.list();
			Collections.reverse(comments);
			session.setAttribute("comments", comments);

			List<UsersVO> users = us.list();
			Collections.reverse(users);
			session.setAttribute("users", users);

			List<MetasVO> metas = ms.listAll();
			Collections.reverse(metas);
			session.setAttribute("metas", metas);

			List<OptionsVO> options = os.list();
			Collections.reverse(options);
			session.setAttribute("options", options);
			if (authorId == uid) {
				request.setAttribute("message", "修改成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts-visitor.jsp");
				return "visitorEditPostSuccess";
			} else {
				request.setAttribute("message", "修改成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-posts.jsp");
				return "adminEditPostSuccess";
			}
		} else {
			List<RelationshipsVO> relationships = rs.list();
			Collections.reverse(relationships);
			session.setAttribute("relationships", relationships);

			List<ContentsVO> contents = cs.list();
			Collections.reverse(contents);
			session.setAttribute("contents", contents);

			List<CommentsVO> comments = cos.list();
			Collections.reverse(comments);
			session.setAttribute("comments", comments);

			List<UsersVO> users = us.list();
			Collections.reverse(users);
			session.setAttribute("users", users);

			List<MetasVO> metas = ms.listAll();
			Collections.reverse(metas);
			session.setAttribute("metas", metas);

			List<OptionsVO> options = os.list();
			Collections.reverse(options);
			session.setAttribute("options", options);
			request.setAttribute("message", "修改成功!");
			request.setAttribute("returnURL", request.getContextPath()
					+ "/admin/manage-pages.jsp");
			return "editPageSuccess";
		}

	}

	/**
	 * 通过id查询文章
	 * 
	 * @return
	 */
	public String find() {
		HttpServletRequest request = ServletActionContext.getRequest();
		ContentsVO content = new ContentsVO();
		content = cs.find(cid);
		if (content != null) {
			request.setAttribute("content", content);
		}
		if (type.equals("post")) {
			return "findPostSuccess";
		} else {
			return "findPageSuccess";
		}
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String op = request.getParameter("multiOption");
		String opreator = request.getParameter("opreatorId");
		if (opreator == null || "".equals(opreator))
			opreator = "-1";
		int opreatorId = Integer.valueOf(opreator);
		int uid = ((UsersVO) session.getAttribute("user")).getUid();

		if (op.equals("multiDel")) {
			for (int i = 0; i < contentIdArray.length; i++) {
				int contentId = Integer.valueOf(contentIdArray[i]);
				List<RelationshipsVO> res = rs.findByCid(contentId);
				for (RelationshipsVO re : res) {
					ms.editCount(re.getMid(), "-");
				}
				cos.delByCid(contentId);
				cs.del(contentId);
				rs.del(contentId);

			}

			/* 获取相关session */

			List<RelationshipsVO> relationships = rs.list();
			Collections.reverse(relationships);
			session.setAttribute("relationships", relationships);

			List<ContentsVO> contents = cs.list();
			Collections.reverse(contents);
			session.setAttribute("contents", contents);

			List<CommentsVO> comments = cos.list();
			Collections.reverse(comments);
			session.setAttribute("comments", comments);

			List<UsersVO> users = us.list();
			Collections.reverse(users);
			session.setAttribute("users", users);

			List<MetasVO> metas = ms.listAll();
			Collections.reverse(metas);
			session.setAttribute("metas", metas);

			List<OptionsVO> options = os.list();
			Collections.reverse(options);
			session.setAttribute("options", options);

			if (type.equals("post")) {
				if (opreatorId == uid) {
					request.setAttribute("message", "删除成功!");
					request.setAttribute("returnURL", request.getContextPath()
							+ "/admin/manage-posts-visitor.jsp");
					return "visitorMultiDelSuccess";
				} else {
					request.setAttribute("message", "删除成功!");
					request.setAttribute("returnURL", request.getContextPath()
							+ "/admin/manage-posts.jsp");
					return "adminMultiDelPostSuccess";
				}
			} else {
				request.setAttribute("message", "删除成功!");
				request.setAttribute("returnURL", request.getContextPath()
						+ "/admin/manage-pages.jsp");
				return "adminMultiDelPageSuccess";
			}
		} else {

			if (opreatorId == uid) {
				return "visitorDoNothing";
			} else {
				if (type.equals("post")) {
					return "adminPostDoNothing";
				} else {
					return "adminPageDoNothing";
				}
			}
		}

	}

}
