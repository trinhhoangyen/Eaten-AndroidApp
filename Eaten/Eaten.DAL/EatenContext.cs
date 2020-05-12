using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata;

namespace Eaten.DAL.Models
{
    public partial class EatenContext : DbContext
    {
        public EatenContext()
        {
        }

        public EatenContext(DbContextOptions<EatenContext> options)
            : base(options)
        {
        }

        public virtual DbSet<BaiDang> BaiDang { get; set; }
        public virtual DbSet<BaiDangTheLoai> BaiDangTheLoai { get; set; }
        public virtual DbSet<HinhAnh> HinhAnh { get; set; }
        public virtual DbSet<TaiKhoan> TaiKhoan { get; set; }
        public virtual DbSet<TheLoai> TheLoai { get; set; }
        public virtual DbSet<TuongTac> TuongTac { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            if (!optionsBuilder.IsConfigured)
            {
#warning To protect potentially sensitive information in your connection string, you should move it out of source code. See http://go.microsoft.com/fwlink/?LinkId=723263 for guidance on storing connection strings.
                optionsBuilder.UseSqlServer("Data Source=.\\THYEN;Initial Catalog=Eaten;Persist Security Info=True;User ID=sa;Password=221199;Pooling=False;MultipleActiveResultSets=False;Encrypt=False;TrustServerCertificate=True;");
            }
        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<BaiDang>(entity =>
            {
                entity.HasKey(e => e.MaBai)
                    .HasName("PK__BaiDang__3520ED7795C48D24");

                entity.Property(e => e.NoiDung).HasColumnType("ntext");

                entity.Property(e => e.TenBai).HasMaxLength(50);

                entity.HasOne(d => d.MaTaiKhoanNavigation)
                    .WithMany(p => p.BaiDang)
                    .HasForeignKey(d => d.MaTaiKhoan)
                    .HasConstraintName("FK__BaiDang__MaTaiKh__398D8EEE");
            });

            modelBuilder.Entity<BaiDangTheLoai>(entity =>
            {
                entity.HasKey(e => new { e.MaBai, e.MaLoai })
                    .HasName("PK__BaiDang___B210480279ACF399");

                entity.ToTable("BaiDang_TheLoai");

                entity.HasOne(d => d.MaBaiNavigation)
                    .WithMany(p => p.BaiDangTheLoai)
                    .HasForeignKey(d => d.MaBai)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__BaiDang_T__MaBai__45F365D3");

                entity.HasOne(d => d.MaLoaiNavigation)
                    .WithMany(p => p.BaiDangTheLoai)
                    .HasForeignKey(d => d.MaLoai)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__BaiDang_T__MaLoa__46E78A0C");
            });

            modelBuilder.Entity<HinhAnh>(entity =>
            {
                entity.HasKey(e => e.MaHinh)
                    .HasName("PK__HinhAnh__13EE108467C02EB8");

                entity.Property(e => e.Hinh).HasColumnType("image");

                entity.HasOne(d => d.MaBaiNavigation)
                    .WithMany(p => p.HinhAnh)
                    .HasForeignKey(d => d.MaBai)
                    .HasConstraintName("FK__HinhAnh__MaBai__3C69FB99");
            });

            modelBuilder.Entity<TaiKhoan>(entity =>
            {
                entity.HasKey(e => e.MaTaiKhoan)
                    .HasName("PK__TaiKhoan__AD7C6529440C09A0");

                entity.Property(e => e.Avatar).HasColumnType("image");

                entity.Property(e => e.Email)
                    .HasMaxLength(50)
                    .IsUnicode(false);

                entity.Property(e => e.GioiTinh).HasMaxLength(10);

                entity.Property(e => e.MatKhau)
                    .HasMaxLength(20)
                    .IsUnicode(false)
                    .IsFixedLength();

                entity.Property(e => e.TenHienThi).HasMaxLength(50);
            });

            modelBuilder.Entity<TheLoai>(entity =>
            {
                entity.HasKey(e => e.MaLoai)
                    .HasName("PK__TheLoai__730A57591C8FC7D9");

                entity.Property(e => e.TenLoai).HasMaxLength(20);
            });

            modelBuilder.Entity<TuongTac>(entity =>
            {
                entity.HasKey(e => new { e.MaBinhLuan, e.MaBai, e.MaTaiKhoan })
                    .HasName("PK__TuongTac__CC3414125E8AB7E7");

                entity.Property(e => e.MaBinhLuan).ValueGeneratedOnAdd();

                entity.Property(e => e.CamXuc).HasDefaultValueSql("((0))");

                entity.Property(e => e.NoiDung).HasColumnType("ntext");

                entity.HasOne(d => d.MaBaiNavigation)
                    .WithMany(p => p.TuongTac)
                    .HasForeignKey(d => d.MaBai)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__TuongTac__MaBai__403A8C7D");

                entity.HasOne(d => d.MaTaiKhoanNavigation)
                    .WithMany(p => p.TuongTac)
                    .HasForeignKey(d => d.MaTaiKhoan)
                    .OnDelete(DeleteBehavior.ClientSetNull)
                    .HasConstraintName("FK__TuongTac__MaTaiK__412EB0B6");
            });

            OnModelCreatingPartial(modelBuilder);
        }

        partial void OnModelCreatingPartial(ModelBuilder modelBuilder);
    }
}
