using EatenAPI.Common.DAL;
using EatenAPI.DAL.Models;
using System;
using System.Collections.Generic;
using System.Text;

namespace EatenAPI.DAL
{
    using Common.Rsp;
    using EatenAPI.DAL.ViewModels;
    using System.Linq;
    public class CommentsRep : GenericRep<EatenDatabaseContext, Comments>
    {
        #region --Override--
        public override Comments Read(int id)
        {
            var res = All.FirstOrDefault(p => p.CommentId == id);
            return res;
        }
        #endregion

        #region
        public int Remove(int id)
        {
            var m = All.First(i => i.CommentId == id);
            m = base.Delete(m);
            return m.CommentId;
        }
        public SingleRsp CreateComment(Comments cmt)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.Comments.Add(cmt);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch (Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }
            }
            return res;
        }
        public SingleRsp UpdateComment(Comments cmt)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.Comments.Update(cmt);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch (Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }
            }
            return res;
        }
        public SingleRsp DeleteComment(Comments cmt)
        {
            var res = new SingleRsp();
            using (var context = new EatenDatabaseContext())
            {
                using (var tran = context.Database.BeginTransaction())
                {
                    try
                    {
                        var t = context.Comments.Remove(cmt);
                        context.SaveChanges();
                        tran.Commit();
                    }
                    catch (Exception ex)
                    {
                        tran.Rollback();
                        res.SetError(ex.StackTrace);
                    }
                }
            }
            return res;
        }
        public IEnumerable<CommentViewModel> GetAllCommentInfo()
        {
            var context = new EatenDatabaseContext();

            var account = context.Accounts.ToList();
            var comment = context.Comments.ToList();

            //join q in quantity on p.PostId equals q.PostId
            var res = from c in comment
                      join a in account on c.AccountId equals a.AccountId
                      select new CommentViewModel
                      {
                          PostId = c.PostId,
                          AccountId = c.AccountId,
                          AvatarURL = a.AvatarURL,
                          DisplayName = a.DisplayName,
                          Content = c.Content
                      };
            return res;
        }
        #endregion
    }
}
