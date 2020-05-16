using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace EatenAPI.DAL.Models
{
    public partial class EatenDatabaseContext : DbContext
    {
        public EatenDatabaseContext()
        {
        }

        public EatenDatabaseContext(DbContextOptions<EatenDatabaseContext> options)
            : base(options)
        {
        }

        public virtual DbSet<Accounts> Accounts { get; set; }
        public virtual DbSet<Comments> Comments { get; set; }
        public virtual DbSet<FoodCategories> FoodCategories { get; set; }
        public virtual DbSet<Pictures> Pictures { get; set; }
        public virtual DbSet<PostFoodCategory> PostFoodCategory { get; set; }
        public virtual DbSet<Posts> Posts { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseSqlServer("Data Source=.\\THYEN;Initial Catalog=EatenDatabase;Persist Security Info=True;User ID=sa;Password=221199;Pooling=False;MultipleActiveResultSets=False;Encrypt=False;TrustServerCertificate=True;");
                //optionsBuilder.UseSqlServer("Data Source=.\\THYEN;Initial Catalog=EatenDatabase;Persist Security Info=True;User ID=sa;Password=221199;Pooling=False;MultipleActiveResultSets=False;Encrypt=False;TrustServerCertificate=True;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<Accounts>(entity =>
            {
                entity.HasKey(e => e.AccountId)
                    .HasName("PK__Accounts__349DA586A1EB9927");

                entity.Property(e => e.AccountId).HasColumnName("AccountID");

                entity.Property(e => e.Avatar).HasColumnType("image");

                entity.Property(e => e.DisplayName).HasMaxLength(50);

                entity.Property(e => e.Email)
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.Gender).HasMaxLength(10);

                entity.Property(e => e.Password)
                    .HasMaxLength(20)
                    .IsUnicode(false)
                    .IsFixedLength();
            });

            modelBuilder.Entity<Comments>(entity =>
            {
                entity.HasKey(e => new { e.CommentId, e.PostId, e.AccountId })
                    .HasName("PK__Comments__DE21640C38572BEB");

                entity.Property(e => e.CommentId)
                    .HasColumnName("CommentID")
                    .ValueGeneratedOnAdd();

                entity.Property(e => e.PostId).HasColumnName("PostID");

                entity.Property(e => e.AccountId).HasColumnName("AccountID");

                entity.Property(e => e.React).HasDefaultValueSql("((0))");

                entity.Property(e => e.Content).HasColumnType("ntext");

                entity.HasOne(d => d.Account)
                    .WithMany(p => p.Comments)
                    .HasForeignKey(d => d.AccountId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Comments__Accoun__412EB0B6");

                entity.HasOne(d => d.Post)
                    .WithMany(p => p.Comments)
                    .HasForeignKey(d => d.PostId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Comments__PostID__403A8C7D");
            });

            modelBuilder.Entity<FoodCategories>(entity =>
            {
                entity.HasKey(e => e.CategoryId)
                    .HasName("PK__FoodCate__19093A2BA389791A");

                entity.Property(e => e.CategoryId).HasColumnName("CategoryID");

                entity.Property(e => e.CategoryName).HasMaxLength(20);
            });

            modelBuilder.Entity<Pictures>(entity =>
            {
                entity.HasKey(e => e.PictureId)
                    .HasName("PK__Pictures__8C2866F8DCB29D77");

                entity.Property(e => e.PictureId).HasColumnName("PictureID");

                entity.Property(e => e.Picture).HasColumnType("image");

                entity.Property(e => e.PostId).HasColumnName("PostID");

                entity.HasOne(d => d.Post)
                    .WithMany(p => p.Pictures)
                    .HasForeignKey(d => d.PostId)
                    .HasConstraintName("FK__Pictures__PostID__3C69FB99");
            });

            modelBuilder.Entity<PostFoodCategory>(entity =>
            {
                entity.HasKey(e => new { e.PostId, e.CategoryId })
                    .HasName("PK__Post_Foo__0B82F39A72E817EA");

                entity.ToTable("Post_FoodCategory");

                entity.Property(e => e.PostId).HasColumnName("PostID");

                entity.Property(e => e.CategoryId).HasColumnName("CategoryID");

                entity.HasOne(d => d.Category)
                    .WithMany(p => p.PostFoodCategory)
                    .HasForeignKey(d => d.CategoryId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Post_Food__Categ__46E78A0C");

                entity.HasOne(d => d.Post)
                    .WithMany(p => p.PostFoodCategory)
                    .HasForeignKey(d => d.PostId)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__Post_Food__PostI__45F365D3");
            });

            modelBuilder.Entity<Posts>(entity =>
            {
                entity.HasKey(e => e.PostId)
                    .HasName("PK__Posts__AA1260389214AD49");

                entity.Property(e => e.PostId).HasColumnName("PostID");

                entity.Property(e => e.AccountId).HasColumnName("AccountID");

                entity.Property(e => e.Address).HasMaxLength(100);

                entity.Property(e => e.Content).HasColumnType("ntext");

                entity.Property(e => e.PostName).HasMaxLength(50);

                entity.HasOne(d => d.Account)
                    .WithMany(p => p.Posts)
                    .HasForeignKey(d => d.AccountId)
                    .HasConstraintName("FK__Posts__AccountID__398D8EEE");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
